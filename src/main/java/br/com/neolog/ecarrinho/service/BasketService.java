package br.com.neolog.ecarrinho.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Basket;
import br.com.neolog.ecarrinho.bean.Product;

@Component
public class BasketService{

	private Basket basket = new Basket();
	
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
		return basket.getProducts();
	}
	
	public void changeAmount(Product product,Long newAmount)
	{
		basket.changeAmount(product, newAmount);
	}
	
	public void remove(Product product)
	{
		basket.remove(product);
	}
	
	public double getTotalValue()
	{
		return basket.getTotalValue();
	}
}