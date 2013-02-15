package br.com.neolog.ecarrinho.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.dao.UserDao;
import br.com.neolog.ecarrinho.util.Persistable;

@Component
@Entity
public class User implements Persistable {

	
	@Id
	@GeneratedValue
	private Long id;
	@Basic
	private String user;
	@Basic
	private String pass;

	public void setUser(String user) {
		if( user.isEmpty() )
			throw new IllegalArgumentException("Empty user name");
		
		this.user = user;
	}

	public void setPass(String pass) {
		if( pass.isEmpty() )
			throw new IllegalArgumentException("Empty password");
		
		this.pass = pass;
	}

	public boolean isValidPassword( String pass ) {
		if( this.pass.equals(pass) )
			return true;
		
		return false;
	}
}
