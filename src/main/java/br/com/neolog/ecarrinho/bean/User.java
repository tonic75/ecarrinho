package br.com.neolog.ecarrinho.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.neolog.ecarrinho.dao.UserDAO;

@Entity
public class User {

	@Transient
	private UserDAO userDAO;

	@Id
	@GeneratedValue
	private int id;
	private String user;
	private String pass;

	@SuppressWarnings("unused")
	private User(){}

	public User( UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isValidUser() {
		User receveidUser = userDAO.get(user);
		if( receveidUser != null && receveidUser.pass.equals(this.pass) )
			return true;
		
		return false;
	}
}
