package br.com.fiap.dao;

import br.com.fiap.exceptions.CodigoInvalidoException;
import br.com.fiap.exceptions.CommitErrorException;

public interface GenericDAO<T, K> {
	
	void cadastrar(T entity);
	void atualizar(T entity);
	void excluir(K id) throws CodigoInvalidoException;
	T buscar(K id);
	void salvar() throws CommitErrorException;
	
}
