package br.com.neolog.ecarrinho.dao;

import java.util.List;

import br.com.neolog.ecarrinho.util.Persistable;

public interface GenericDao<T extends Persistable> {
	public T get(Long id);
	public List<T> getAll();
	public void save(T object);
	public void delete(T object);	
}
