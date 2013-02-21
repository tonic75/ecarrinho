package br.com.neolog.ecarrinho.forms;

import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.service.BasketService;
import br.com.neolog.ecarrinho.util.WrapLayout;

@Component
public class ProductsOnBasketHolder extends JPanel {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BasketService basketService;
	
	JTextField totalValue;
	
	public ProductsOnBasketHolder()
	{
		this.setLayout(new WrapLayout(FlowLayout.LEFT,0,0));		
	}
	
	public void refreshBasket()
	{
		removeAll();
		Map<Product, Long> productsOnBasket = basketService.getBasket();
		refreshProducts(productsOnBasket);
		refreshTotalValue(productsOnBasket);
		validate();
		repaint();
	}	
	
	private void refreshProducts( Map<Product, Long> productsOnBasket )
	{
		
		for (Product product : productsOnBasket.keySet()) {
			add(new ProductOnBasketPanel(product, productsOnBasket.get(product), this));
		}
	}
	
	private void refreshTotalValue( Map<Product, Long> productsOnBasket )
	{
		double totalValue = 0;
		for (Product product : productsOnBasket.keySet()) {
			totalValue += product.getPrice()*productsOnBasket.get(product);
		}
		this.totalValue.setText(String.valueOf(totalValue));
	}
	
	public void changeAmount(Product product,Long newAmount)
	{
		basketService.changeAmount(product, newAmount);
	}
	
	public void remove(Product product)
	{
		basketService.remove(product);
	}
	
	public void setTotalValue( JTextField totalValue )
	{
		this.totalValue = totalValue;
	}
}