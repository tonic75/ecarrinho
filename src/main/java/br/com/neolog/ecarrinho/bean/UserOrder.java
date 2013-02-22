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

import br.com.neolog.ecarrinho.util.Persistable;

@Entity
@Access(AccessType.FIELD)
public class UserOrder implements Persistable{

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
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
	
	@SuppressWarnings("unused")
	private UserOrder(){}
	
	

	public UserOrder(Basket basket, Calendar orderTime, User user,
			String address, String card, String agency, String accNumber,
			PaymentMethod paymentMethod) {
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



	public Basket getBasket() {
		return basket;
	}

	public Calendar getOrderTime() {
		return orderTime;
	}

	public User getUser() {
		return user;
	}

}