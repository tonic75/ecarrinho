package br.com.neolog.ecarrinho.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.dao.UserDao;

@Component
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public boolean isValidPassword( String userName, String pass )
	{
		try
		{
			User user = dao.get(userName);
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
