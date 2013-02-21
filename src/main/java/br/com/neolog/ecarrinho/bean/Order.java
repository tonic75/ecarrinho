package br.com.neolog.ecarrinho.bean;

import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Order {

	@Id
	private Long id;
	
	@OneToOne
	private Basket basket;
	
	@Basic
	private Calendar orderTime;
	
	@ManyToOne
	private User user;
	
	@SuppressWarnings("unused")
	private Order(){}
	
	public Order(Long id, Basket basket, Calendar orderTime, User user) {
		super();
		this.id = id;
		this.basket = basket;
		this.orderTime = orderTime;
		this.user = user;
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