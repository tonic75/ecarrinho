package br.com.neolog.ecarrinho.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.neolog.ecarrinho.util.Persistable;

import com.google.common.base.Objects;

/**
 * @author antonio.moreira
 * 
 * The Class User represents a user with his user name and password.
 */
@Entity
@Access(AccessType.FIELD)
public class User implements Persistable {

	@Id
	private String user;
	
	@Basic
	private String pass;

	/**
	 * Empty private default constructor for hibernate.
	 */
	@SuppressWarnings("unused")
	private User(){}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param user the user
	 * @param pass the pass
	 */
	public User(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user name
	 */
	public String getUser()
	{
		return user;
	}
	
	/**
	 * Checks if the password passed is valid.
	 *
	 * @param pass the pass
	 * @return true, if the password is valid
	 */
	public boolean isValidPassword( String pass ) {
		if( this.pass.equals(pass) )
			return true;
		
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		final User user = (User)obj;
		return Objects.equal(this.user, user.user);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("user", user).toString();
	}
}
