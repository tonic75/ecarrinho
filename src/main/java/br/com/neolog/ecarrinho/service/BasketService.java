package br.com.neolog.ecarrinho.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Basket;
import br.com.neolog.ecarrinho.bean.Product;

/**
 * The service for the entity Basket.
 * 
 * @author antonio.moreira
 */
@Component
public class BasketService{

	/** 
	 * Creates a new basket in here in order to not allow anyone else to autowire one.
	*/
	private Basket basket = new Basket();
	
	/**
	 * Adds the to basket.
	 *
	 * @param product the product
	 * @param amount the amount
	 */
	public void addToBasket( Product product, Long amount )
	{
		basket.addToBasket(product, amount);
	}
	
	public Basket getBasket()
	{
		return basket;
	}
	

	public Map<Product, Long> getProducts()
	{
		return basket.getBasket();
	}
	
	/**
	 * Change amount.
	 *
	 * @param product the product
	 * @param newAmount the new amount
	 */
	public void changeAmount(Product product,Long newAmount)
	{
		basket.changeAmount(product, newAmount);
	}
	
	public void remove(Product product)
	{
		basket.remove(product);
	}
	
	public void newBasket()
	{
		basket = new Basket();
	}
	
	/**
	 * Gets the total value of the products in the basket considering the amount of each.
	 *
	 * @return the total value
	 */
	public double getTotalValue()
	{
		return basket.getTotalValue();
	}
}