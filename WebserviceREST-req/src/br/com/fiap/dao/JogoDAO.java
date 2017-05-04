package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.entity.Jogo;

public interface JogoDAO extends GenericDAO<Jogo, Long> {
	
	List<Jogo> listar();

}
