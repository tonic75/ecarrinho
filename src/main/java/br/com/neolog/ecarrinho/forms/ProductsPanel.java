package br.com.neolog.ecarrinho.forms;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.service.ProductService;
import br.com.neolog.ecarrinho.util.WrapLayout;

@Component
public class ProductsPanel extends JPanel{
	
	@Autowired
	ProductService productService;
	
	JPanel productsPanel;

	private static final long serialVersionUID = 1L;
	
	public ProductsPanel()
	{		
		productsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
		
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		scroll.getViewport().add(productsPanel);
		
		add(scroll);
	}
	
	public void loadAllProducts()
	{
		List<Product> allProducts = productService.getAllProducts();
		for( Product prod : allProducts )
		{
			productsPanel.add(new ProductSpot(prod)); 
		}
	}
}