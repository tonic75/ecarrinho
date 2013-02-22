package br.com.neolog.ecarrinho.service;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Session;
import br.com.neolog.ecarrinho.bean.User;

@Component
public class SessionService {

	private Session session = new Session();

	public User getLoggedUser() {
		return session.getLoggedUser();
	}

	public void logIn(User loggedUser) {
		session.logIn(loggedUser);
	}	
	
	public void LogOut()
	{
		session.logOut();
	}
	
	public boolean isAnyoneLogged()
	{
		return session.isAnyoneLogged();
	}
}
