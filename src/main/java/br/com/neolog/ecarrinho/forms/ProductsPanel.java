package br.com.neolog.ecarrinho.forms;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.service.ProductService;
import br.com.neolog.ecarrinho.util.WrapLayout;

@Component
public class ProductsPanel extends JScrollPane{
	
	@Autowired
	ProductService productService;
	
	JPanel panel;

	private static final long serialVersionUID = 1L;
	
	public ProductsPanel()
	{		
		//productsPanel = new JPanel(new WrapLayout());
		//setSize(new Dimension(800,800));
		panel = new JPanel(new WrapLayout());
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		getViewport().add(panel);
	}
	
	public void loadAllProducts()
	{
		List<Product> allProducts = productService.getAllProducts();
		for( Product prod : allProducts )
		{
			panel.add(new ProductSpot(prod));
		}
	}
}