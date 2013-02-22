package br.com.neolog.ecarrinho.bean;

public class Session {

	private User loggedUser;

	public User getLoggedUser() {
		return loggedUser;
	}

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
	
	public void logOut()
	{
		loggedUser = null;
	}
	
	public boolean isAnyoneLogged()
	{
		return loggedUser == null ? false : true;
	}
}
