package br.com.neolog.ecarrinho.bean;

import java.util.Calendar;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.google.common.base.Objects;

import br.com.neolog.ecarrinho.util.Persistable;

/**
 * The Class UserOrder. This class represents a order that a user do. It
 * contains all the information necessary for it: - The basket with the products
 * and the amounts. - When the order was made. - The user that did it. - The
 * address to shipment. - The card for payment. - The agency number for debt. -
 * The account number for debt. - The type of the payment.
 * 
 * @author antonio.moreira
 */
@Entity
@Access( AccessType.FIELD )
public class UserOrder implements Persistable
{

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne( cascade = CascadeType.ALL )
	private Basket basket;

	@Basic
	private Calendar orderTime;

	@ManyToOne
	private User user;

	@Basic
	private String address;

	@Basic
	private String card;

	@Basic
	private String agency;

	@Basic
	private String accNumber;

	@Enumerated
	private PaymentMethod paymentMethod;

	/**
	 * Empty private default constructor for hibernate.
	 */
	@SuppressWarnings( "unused" )
	private UserOrder()
	{
	}

	/**
	 * Instantiates a new user order.
	 * 
	 * @param basket
	 *            the basket
	 * @param orderTime
	 *            the order time
	 * @param user
	 *            the user
	 * @param address
	 *            the address
	 * @param card
	 *            the card
	 * @param agency
	 *            the agency
	 * @param accNumber
	 *            the acc number
	 * @param paymentMethod
	 *            the payment method
	 */
	public UserOrder( Basket basket, Calendar orderTime, User user, String address, String card, String agency, String accNumber,
			PaymentMethod paymentMethod )
	{
		super();
		this.basket = basket;
		this.orderTime = orderTime;
		this.user = user;
		this.address = address;
		this.card = card;
		this.agency = agency;
		this.accNumber = accNumber;
		this.paymentMethod = paymentMethod;
	}

	public Basket getBasket()
	{
		return basket;
	}

	public Calendar getOrderTime()
	{
		return orderTime;
	}

	public User getUser()
	{
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode( basket, user, orderTime );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		final UserOrder userOrder = (UserOrder) obj;
		return Objects.equal( this.basket, userOrder.basket ) && Objects.equal( this.user, userOrder.user )
				&& Objects.equal( this.orderTime, userOrder.orderTime );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Objects.toStringHelper( this ).add( "User: ", user ).add( "Basket: ", basket.getBasket() ).toString();
	}

}