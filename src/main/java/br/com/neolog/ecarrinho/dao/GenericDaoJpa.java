package br.com.neolog.ecarrinho.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.ecarrinho.util.Persistable;

public class GenericDaoJpa<T extends Persistable> implements GenericDao<T> {
	
	private Class<T> type;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unused")
	private GenericDaoJpa(){}
	
	public GenericDaoJpa( Class<T> type )
	{
		this.type = type;
	}
	
	@Transactional(readOnly=true)
	public T get(Long id) {
		if( id == null )
			return null;
		else
			return entityManager.find(type, id);
	}

	@Transactional(readOnly=true)
	public List<T> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> from = criteriaQuery.from(type);
		CriteriaQuery<T> select = criteriaQuery.select(from);
		TypedQuery<T> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();		
	}

	public void save(T object) {
		entityManager.persist(object);		
	}

	public void delete(T object) {
		entityManager.remove(object);		
	}

}
