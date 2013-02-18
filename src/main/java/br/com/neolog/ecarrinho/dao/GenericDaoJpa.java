package br.com.neolog.ecarrinho.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.ecarrinho.util.Persistable;

// TODO: Auto-generated Javadoc
/**
 * @author antonio.moreira
 * 
 * Implentation of a generic dao with CRUD operations.
 *
 * @param <T> The class that is going to be persisted
 * @param <U> The type of the PK
 */
@Repository
public abstract class GenericDaoJpa<T extends Persistable, U> implements GenericDao<T, U> {
	
	/** The type of the class that is going to managed. */
	private Class<T> type;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Empty private default constructor for hibernate.
	 */
	@SuppressWarnings("unused")
	private GenericDaoJpa(){}
	
	/**
	 * Instantiates a new generic dao jpa.
	 * It's called by the sub-classes constructors
	 *
	 * @param type of the class that is going to managed 
	 */
	public GenericDaoJpa( Class<T> type )
	{
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see br.com.neolog.ecarrinho.dao.GenericDao#get(java.lang.Object)
	 */
	@Transactional(readOnly=true)
	public T get(U id) {
		if( id == null )
			return null;
		else
			return entityManager.find(type, id);
	}

	/* (non-Javadoc)
	 * @see br.com.neolog.ecarrinho.dao.GenericDao#getAll()
	 */
	@Transactional(readOnly=true)
	public List<T> getAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<T> from = criteriaQuery.from(type);
		CriteriaQuery<T> select = criteriaQuery.select(from);
		TypedQuery<T> typedQuery = entityManager.createQuery(select);
		return typedQuery.getResultList();		
	}

	/* (non-Javadoc)
	 * @see br.com.neolog.ecarrinho.dao.GenericDao#save(br.com.neolog.ecarrinho.util.Persistable)
	 */
	@Transactional
	public void save(T object) {
		entityManager.persist(object);		
	}

	/* (non-Javadoc)
	 * @see br.com.neolog.ecarrinho.dao.GenericDao#delete(br.com.neolog.ecarrinho.util.Persistable)
	 */
	@Transactional
	public void delete(T object) {
		entityManager.remove(entityManager.merge(object));		
	}


}
