package br.com.neolog.ecarrinho.bean;


/**
 * The Class Session.
 * Represents a logged user in the system.
 * Only one user can be logged at a time.
 * If there is no user logged, the variable loggedUser will be null.
 * 
 * @author antonio.moreira
 */
public class Session {

	/** The logged user. */
	private User loggedUser;

	/**
	 * Gets the logged user.
	 *
	 * @return the logged user
	 */
	public User getLoggedUser() {
		return loggedUser;
	}

	/**
	 * Log in.
	 * Receives a user to be logged into the system.
	 *
	 * @param loggedUser the logged user
	 */
	public void logIn(User loggedUser) {
		if( loggedUser != null )
		{
			this.loggedUser = loggedUser;
		}
		else
		{
			throw new IllegalArgumentException("null user");
		}
	}	
	
	/**
	 * Log out.
	 */
	public void logOut()
	{
		loggedUser = null;
	}
	
	/**
	 * Checks if is anyone logged.
	 *
	 * @return true, if is anyone logged
	 */
	public boolean isAnyoneLogged()
	{
		return loggedUser == null ? false : true;
	}
}
