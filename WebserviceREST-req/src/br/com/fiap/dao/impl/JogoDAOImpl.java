package br.com.fiap.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.JogoDAO;
import br.com.fiap.entity.Jogo;

public class JogoDAOImpl extends GenericDAOImpl<Jogo, Long> implements JogoDAO {

	public JogoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Jogo> listar() {
		return em.createQuery("from Jogo", Jogo.class).getResultList();
	}

}
