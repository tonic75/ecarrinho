package br.com.neolog.ecarrinho.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Basket;
import br.com.neolog.ecarrinho.bean.Product;

@Component
public class BasketService{

	@Autowired
	private Basket basket;
	
	public void addToBasket( Product product, Long amount )
	{
		basket.addToBasket(product, amount);
	}
	
	public Map<Product, Long> getBasket()
	{
		return basket.getBasket();
	}
	
	public void changeAmount(Product product,Long newAmount)
	{
		basket.changeAmount(product, newAmount);
	}
	
	public void remove(Product product)
	{
		basket.remove(product);
	}
}