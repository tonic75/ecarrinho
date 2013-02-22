package br.com.neolog.ecarrinho.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.dao.UserDao;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User getUser( String userName )
	{
		return userDao.get(userName);
	}
	
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
