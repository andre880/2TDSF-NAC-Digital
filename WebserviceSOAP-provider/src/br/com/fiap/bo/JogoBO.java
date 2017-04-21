package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.axis2.AxisFault;

import br.com.fiap.dao.JogoDAO;
import br.com.fiap.dao.impl.JogoDAOImpl;
import br.com.fiap.entity.Jogo;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class JogoBO {

	public void cadastrar(Jogo entity) throws AxisFault {
		EntityManager em = EntityManagerFactorySingleton.getInst().createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		dao.cadastrar(entity);
		try {
			dao.salvar();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AxisFault("Erro no cadastro de jogo");
		} finally {
			em.close();
		}
	}

	public Jogo buscar(long id) throws AxisFault {
		EntityManager em = EntityManagerFactorySingleton.getInst().createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);

		Jogo jogo = null;

		try {
			jogo = dao.buscar(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AxisFault("Erro ao buscar jogo");
		} finally {
			em.close();
		}

		return jogo;
	}	
	
	public List<Jogo> listar() throws AxisFault {
		EntityManager em = EntityManagerFactorySingleton.getInst().createEntityManager();
		JogoDAO dao = new JogoDAOImpl(em);
		
		List<Jogo> jogos = null;

		try {
			jogos = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			throw new AxisFault("Erro ao listar jogos");
		} finally {
			em.close();
		}
		
		return jogos;
	}

}
