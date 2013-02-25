package br.com.neolog.ecarrinho.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.dao.UserDao;

/**
 * The service for the User.
 * 
 * @author antonio.moreira
 */
@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * Gets the user by its user name.
	 *
	 * @param userName the user name
	 * @return the user
	 */
	public User getUser( String userName )
	{
		return userDao.get(userName);
	}
	
	/**
	 * Register a new User respecting a restriction that there cannot be two user with the same user name.
	 *
	 * @param user the user
	 */
	public void registerUser(User user)
	{
		if( user.isValidUser() )
		{
			userDao.save(user);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Checks if the password of the user passed is valid.
	 *
	 * @param userName the user name
	 * @param pass the pass
	 * @return true, if is valid password
	 */
	public boolean isValidPassword( String userName, String pass )
	{
		try
		{
			User user = userDao.get(userName);
			if( user.isValidPassword(String.valueOf(pass)) )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch( NoResultException e1 )
		{
			return false;
		}
		catch( NullPointerException e2 )
		{
			return false;
		}
	}

}
