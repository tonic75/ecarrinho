package br.com.neolog.ecarrinho.service;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Session;
import br.com.neolog.ecarrinho.bean.User;

/**
 * The service for Session
 * 
 * @author antonio.moreira
 */
@Component
public class SessionService
{

	/**
	 * Creates a new Session in here in order to not allow anyone else to
	 * autowire one..
	 */
	private Session session = new Session();

	/**
	 * Gets the logged user.
	 * 
	 * @return the logged user
	 */
	public User getLoggedUser()
	{
		return session.getLoggedUser();
	}

	/**
	 * Log in.
	 * 
	 * @param loggedUser
	 *            the logged user
	 */
	public void logIn( User loggedUser )
	{
		session.logIn( loggedUser );
	}

	/**
	 * Log out.
	 */
	public void LogOut()
	{
		session.logOut();
	}

	/**
	 * Checks if anyone is logged.
	 * 
	 * @return true, if anyone is logged
	 */
	public boolean isAnyoneLogged()
	{
		return session.isAnyoneLogged();
	}
}
