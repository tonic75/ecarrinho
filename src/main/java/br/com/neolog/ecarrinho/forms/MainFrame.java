package br.com.neolog.ecarrinho.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.service.CategoryService;
import br.com.neolog.ecarrinho.service.ProductService;

import com.google.common.collect.Multimap;
import com.jgoodies.forms.debug.FormDebugPanel;

@Component
public class MainFrame extends JFrame implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	DefaultListModel categoriesListModel = new DefaultListModel();
	JList categoriesList = new JList(categoriesListModel);
	JScrollPane categoriesScroll;
	JPanel categoriesContainer = new JPanel();
	JPanel productsContainer;
	JPanel filterPanel;
	JTextField filterField;
	
	Multimap<Category, Product> productsByCategoryMap;
	
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
		setMinimumSize(new Dimension(765,550));
		
		productsContainer.setBorder(BorderFactory.createLineBorder(Color.black));
		
		categoriesList.addListSelectionListener(this);
		categoriesScroll.getViewport().add(categoriesContainer.add(categoriesList));
		categoriesScroll.setBorder(BorderFactory.createLineBorder(Color.black));
		
		filterPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void inicialLoad()
	{
		productsByCategoryMap = productService.getAllProductsByCategory();
		loadProducts();
		loadCategories();
	}
	
	public void loadProducts()
	{
		productsPanel.loadAllProducts();
		productsContainer.add(productsPanel);
	}
	
	public void loadCategories()
	{
		List<Category> categories = categoryService.getAllCategories();
		categoriesListModel.add(0, "Todas as categorias");
		for (Category category : categories) {
			categoriesListModel.addElement(category.getName());
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		filterByCategory();
	}
	
	public void filterByCategory()
	{
		if( categoriesList.getSelectedIndex() == 0 )
		{
			productsPanel.loadAllProducts();
		}
		else
		{
			loadProductsByCategory( (String) categoriesList.getSelectedValue());
		}
		refreshUI();
	}
	
	public void loadProductsByCategory(String categoryName)
	{
		List<Product> products = (List<Product>) productsByCategoryMap.get(categoryService.getCategory(categoryName));
		productsPanel.loadListProducts(products);
	}
	
	public Action filter = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			productsPanel.loadListProducts(productService.getProductsByDescription( filterField.getText() ));
			refreshUI();
		}
	};
	
	public Action clean = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			filterField.setText("");
			categoriesList.setSelectedIndex(0);
			filterByCategory();
		}
	};
	
	
	
	public void refreshUI()
	{
		validate();
		repaint();
	}
}