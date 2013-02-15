package br.com.neolog.ecarrinho.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.User;

@Component(value="dao")
public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public UserDaoJpa() {
		super(User.class);
	}

	public User getUserbyUserName(String user) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = userQuery.from(User.class);
		Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
		userQuery.where(userPredicate);
		return entityManager.createQuery(userQuery).getSingleResult();
	}
}
