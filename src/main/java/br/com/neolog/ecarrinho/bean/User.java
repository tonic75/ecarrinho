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
 *         The Class User represents a user with his user name and password.
 */
@Entity
@Access(AccessType.FIELD)
public class User implements Persistable {

	@Id
	private String user;

	@Basic
	private String pass;

	@Basic
	private String name;

	@Basic
	private String address;

	@Basic
	private String cardNumber;

	@Basic
	private String CPF;

	/**
	 * Empty private default constructor for hibernate.
	 */
	@SuppressWarnings("unused")
	private User() {
	}

	/**
	 * Instantiates a new user.
	 * 
	 * @param user
	 *            the user
	 * @param pass
	 *            the pass
	 */
	public User(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}

	public User(String user, String pass, String name, String address,
			String cardNumber, String CPF) {
		this.user = user;
		this.pass = pass;
		this.name = name;
		this.address = address;
		this.cardNumber = cardNumber;
		this.CPF = CPF;
	}

	public String getUser() {
		return user;
	}

	/**
	 * Checks if the password passed is valid.
	 * 
	 * @param pass
	 *            the pass
	 * @return true, if the password is valid
	 */
	public boolean isValidPassword(String pass) {
		if (this.pass.equals(pass))
			return true;

		return false;
	}

	/**
	 * Checks if this is valid user checking if the required fields are not empty.
	 *
	 * @return true, if the user is valid
	 */
	public boolean isValidUser()
	{
		if (!name.equals("") && !CPF.equals("") && !user.equals("") && !pass.equals(""))
			return true;
		
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		final User user = (User) obj;
		return Objects.equal(this.user, user.user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("user", user).toString();
	}
}
