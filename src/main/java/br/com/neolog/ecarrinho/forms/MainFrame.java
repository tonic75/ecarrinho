package br.com.neolog.ecarrinho.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import com.jgoodies.forms.layout.CellConstraints;

@Component
public class MainFrame extends JFrame implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel categoriesListModel = new DefaultListModel();
	private JList categoriesList = new JList(categoriesListModel);
	private JScrollPane categoriesScroll;
	private JPanel categoriesContainer = new JPanel();
	private JScrollPane productsContainer;
	private JPanel filterPanel;
	private JTextField filterField;
	private JPanel basketLoginPanel;
	private JButton logInBtn = new JButton("Entrar");
	private JButton registerBtn = new JButton("Registrar");
	
	private CellConstraints cc = new CellConstraints();
	
	private Multimap<Category, Product> productsByCategoryMap;
	
	@Autowired
	private BasketFrame basketPanel;
	@Autowired
	private ProductsPanel productsPanel;
	@Autowired
	private LoginFrame loginFrame;
	@Autowired
	private RegisterFrame registerFrame;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

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
		putActionsOnButtons();
		updateUiSessionStatus();
		basketPanel.initialize();
	}
	
	private void loadProducts()
	{
		productsPanel.loadProducts();
		productsContainer.getViewport().add(productsPanel);
	}
	
	private void loadCategories()
	{
		List<Category> categories = categoryService.getAllCategories();
		categoriesListModel.add(0, "Todas os produtos");
		for (Category category : categories) {
			categoriesListModel.addElement(category.getName());
		}
	}

	public void updateUiSessionStatus()
	{
		// TODO: SESSION!
		basketLoginPanel.add(logInBtn, cc.xy(4,2));
		basketLoginPanel.add(registerBtn, cc.xy(5,2));
	}
	
	public void putActionsOnButtons()
	{
		logInBtn.addActionListener(logIn);
		registerBtn.addActionListener(register);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		filterByCategory();
	}
	
	private void filterByCategory()
	{
		if( categoriesList.getSelectedIndex() == 0 )
		{
			productsPanel.loadProducts();
		}
		else
		{
			loadProductsByCategory( (String) categoriesList.getSelectedValue());
		}
		refreshUI();
	}
	
	private void loadProductsByCategory(String categoryName)
	{
		List<Product> products = (List<Product>) productsByCategoryMap.get(categoryService.getCategory(categoryName));
		productsPanel.loadProducts(products);
	}
	
	public Action filter = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			productsPanel.loadProducts(productService.getProductsByDescription( filterField.getText() ));
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
	
	public Action showBasket = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			basketPanel.setVisible(true);
		}
	};
	
	public Action logIn = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			loginFrame.setVisible(true);
		}
	};
	
	public Action register = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			registerFrame.setVisible(true);
		}
	};
	
	private void refreshUI()
	{
		validate();
		repaint();
	}
}