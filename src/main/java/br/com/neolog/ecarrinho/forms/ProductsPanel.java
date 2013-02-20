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

/**
 * The Class ProductsPanel.
 * This is a JScrollPane with a panel that holds products on it.
 * It can show all the products persisted or only a list of them.
 *
 * @author antonio.moreira
 */
@Component
public class ProductsPanel extends JScrollPane{
	
	@Autowired
	ProductService productService;

	JPanel panel;

	private static final long serialVersionUID = 1L;
	
	public ProductsPanel()
	{		
		panel = new JPanel(new WrapLayout(FlowLayout.LEFT,0,0));
		
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getViewport().add(panel);
	}
	
	/**
	 * Load all products persisted.
	 */
	public void loadAllProducts()
	{
		panel.removeAll();
		List<Product> allProducts = productService.getAllProducts();
		for( Product product : allProducts )
		{
			panel.add(new ProductSpot(product));
		}
	}
	
	/**
	 * Load a list of products.
	 *
	 * @param products the products to be showed
	 */
	public void loadListProducts( List<Product> products )
	{
		panel.removeAll();
		for (Product product : products) {
			panel.add(new ProductSpot(product));
		}		
	}
}