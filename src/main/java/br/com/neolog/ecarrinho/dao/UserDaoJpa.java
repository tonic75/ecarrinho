package br.com.neolog.ecarrinho.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.User;

/**
 * @author antonio.moreira
 *
 * The final dao for User.
 * Passes his type for GenericDaoJpa constructor.
 */
@Component
public class UserDaoJpa extends GenericDaoJpa<User, String> implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public UserDaoJpa() {
		super(User.class);
	}
}
