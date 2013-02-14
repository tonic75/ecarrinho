package br.com.neolog.ecarrinho.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.ecarrinho.bean.User;

public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	/*public void isUser( String user, String pass )
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = userQuery.from(User.class);
		Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
		Predicate passPredicate = criteriaBuilder.equal(root.get("pass"), pass);
		userQuery.where(criteriaBuilder.and(userPredicate,passPredicate));
		try
		{
			entityManager.createQuery(userQuery).getSingleResult();
		}
		catch( NoResultException e )
		{
			System.out.println("Usuário não encontrado");
		}
	}
	*/
	
	public User get( String user )
	{
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = userQuery.from(User.class);
		Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
		userQuery.where(userPredicate);
		
		try
		{
			return entityManager.createQuery(userQuery).getSingleResult();
		}
		catch( NoResultException e )
		{
			return null;
		}
	}
	
	@Transactional
	public void save( User user )
	{
		entityManager.persist(user);
	}
}
