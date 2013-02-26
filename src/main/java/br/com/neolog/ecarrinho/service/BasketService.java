package br.com.neolog.ecarrinho.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Basket;
import br.com.neolog.ecarrinho.bean.Product;

/**
 * The service for the entity Basket.
 * 
 * @author antonio.moreira
 */
@Component
public class BasketService {

	/**
	 * Creates a new basket in here in order to not allow anyone else to
	 * autowire one.
	 */
	private Basket basket = new Basket();

	@Autowired
	private AcquisitionService acquisitionService;

	public Basket getBasket() {
		return basket;
	}

	public Map<Product, Long> getProducts() {
		return basket.getBasket();
	}
	
	/**
	 * Adds the to basket.
	 * 
	 * @param product
	 *            the product
	 * @param amount
	 *            the amount
	 * 
	 * @throws IllegalArgumentException
	 *             if the amount of the product to be added to the basket is not
	 *             available in stock
	 */
	public void addToBasket(Product product, Long amount) {
		long amountOnBasketNow = 0;
		if( basket.getBasket().containsKey(product))
		{
			amountOnBasketNow = basket.getBasket().get(product);
		}
		checkStockAmount(product, amountOnBasketNow + amount);
		basket.addToBasket(product, amount);
	}

	/**
	 * Change amount.
	 * 
	 * @param product
	 *            the product
	 * @param newAmount
	 *            the new amount
	 * 
	 * @throws IllegalArgumentException
	 *             if the amount of the product to be added to the basket is not
	 *             available in stock
	 */
	public void changeAmount(Product product, Long newAmount) {
		checkStockAmount(product, newAmount);
		basket.changeAmount(product, newAmount);
	}
	
	public void checkStockAmount(Product product, Long amount)
	{
		if( acquisitionService.stockAmount(product) < amount )
		{
			throw new IllegalArgumentException(
					"Not enough product amount in stock");
		}
	}

	public void remove(Product product) {
		basket.remove(product);
	}

	public void newBasket() {
		basket = new Basket();
	}

	/**
	 * Gets the total value of the products in the basket considering the amount
	 * of each.
	 * 
	 * @return the total value
	 */
	public double getTotalValue() {
		return basket.getTotalValue();
	}

	public boolean isEmpty() {
		return basket.getBasket().isEmpty();
	}
}