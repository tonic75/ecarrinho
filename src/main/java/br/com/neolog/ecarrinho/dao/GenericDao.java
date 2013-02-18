package br.com.neolog.ecarrinho.dao;

import java.util.List;

import br.com.neolog.ecarrinho.util.Persistable;

/**
 * @author antonio.moreira
 *
 * @param <T> The class that is going to be persisted
 * @param <U> The type of the PK
 */
public interface GenericDao<T extends Persistable, U> {
	public T get(U id);
	public List<T> getAll();
	public void save(T object);
	public void delete(T object);	
}
