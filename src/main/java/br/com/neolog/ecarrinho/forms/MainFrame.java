package br.com.neolog.ecarrinho.forms;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.service.CategoryService;
import br.com.neolog.ecarrinho.service.ProductService;

import com.jgoodies.forms.debug.FormDebugPanel;

@Component
public class MainFrame extends JFrame implements ListSelectionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JList listCategories;
	JPanel categoriesContainer;
	JPanel productsContainer;
	@Autowired
	ProductsPanel productsPanel;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	public MainFrame()
	{
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel", FormDebugPanel.class);
			add(swingEngine.render("swixml/"+this.getClass().getSimpleName()+".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		categoriesContainer.setLayout(new BoxLayout(categoriesContainer, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(850,500));
	}
	
	public void inicialLoad()
	{
		loadProducts();
		loadCategories();
		listCategories.addListSelectionListener(this);
	}
	
	public void loadProducts()
	{
		productsPanel.loadAllProducts();
		productsContainer.add(productsPanel);
	}
	
	public void loadCategories()
	{
		List<Category> categories = categoryService.getAllCategories();
		listCategories = new JList(categories.toArray());
		categoriesContainer.add(listCategories);		
	}

	public void valueChanged(ListSelectionEvent e) {
		System.out.println("papiu");
	}
}