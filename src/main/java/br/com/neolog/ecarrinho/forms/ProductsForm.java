package br.com.neolog.ecarrinho.forms;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.ProductDao;
import br.com.neolog.ecarrinho.util.WrapLayout;

@Component
public class ProductsForm extends JFrame{
	
	@Autowired
	ProductDao dao;
	
	JPanel productsPanel;

	private static final long serialVersionUID = 1L;
	
	public ProductsForm()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		
		productsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT));
		productsPanel.setVisible(true);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		scroll.getViewport().add(productsPanel);
		
		add(scroll);
		
		setVisible(true);
	}
	
	public void loadAllProducts()
	{
		List<Product> allProducts = dao.getAll();
		for( Product prod : allProducts )
		{
			productsPanel.add(new ProductSpace(prod)); 
		}
	}
}