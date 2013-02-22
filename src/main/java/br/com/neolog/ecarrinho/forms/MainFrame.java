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
import javax.swing.JLabel;
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
import br.com.neolog.ecarrinho.service.SessionService;
import br.com.neolog.ecarrinho.util.JFrameAspectAdapter;

import com.google.common.collect.Multimap;
import com.jgoodies.forms.debug.FormDebugPanel;

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
	private JButton logInBtn;
	private JButton registerBtn;
	private JLabel userName;
	private JButton exitBtn;
	
	@Autowired
	JFrameAspectAdapter aspectAdapter;
	
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
	private PaymentFrame paymentFrame;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SessionService session;

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
		
		basketLoginPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void inicialLoad()
	{
		productsByCategoryMap = productService.getAllProductsByCategory();
		loadProducts();
		loadCategories();
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
		if(session.getLoggedUser() == null)
		{
			userName.setVisible(false);
			exitBtn.setVisible(false);
			logInBtn.setVisible(true);
			registerBtn.setVisible(true);
		}
		else
		{
			logInBtn.setVisible(false);
			registerBtn.setVisible(false);
			userName.setText(session.getLoggedUser().getName());
			userName.setVisible(true);
			exitBtn.setVisible(true);
		}
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
			setVisible(false);
			loginFrame.setVisible(true);
		}
	};
	
	public Action register = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			registerFrame.setVisible(true);
		}
	};
	
	public Action exit = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			session.LogOut();
			updateUiSessionStatus();
		}
	};
	
	public Action finalizeBuy = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			finalizeBuy();
		}
	};
	
	public void finalizeBuy()
	{
		aspectAdapter.windowCaller(paymentFrame);
		setVisible(false);
		paymentFrame.setVisible(true);
	}
	
	@Override
	public void setVisible( boolean aFlag )
	{
		updateUiSessionStatus();
		super.setVisible(aFlag);
	}
	
	private void refreshUI()
	{
		validate();
		repaint();
	}
}