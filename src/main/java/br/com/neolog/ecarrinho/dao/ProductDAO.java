package br.com.neolog.ecarrinho.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.ecarrinho.bean.Product;

public class ProductDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void save( Product product )
	{
		entityManager.persist(product);
	}
}