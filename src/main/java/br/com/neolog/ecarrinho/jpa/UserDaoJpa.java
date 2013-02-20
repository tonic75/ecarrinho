package br.com.neolog.ecarrinho.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.dao.UserDao;

/**
 * @author antonio.moreira
 *
 * The final DAO for User.
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
