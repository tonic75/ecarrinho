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
import br.com.neolog.ecarrinho.forms.adapters.Adapter;
import br.com.neolog.ecarrinho.service.CategoryService;
import br.com.neolog.ecarrinho.service.ProductService;
import br.com.neolog.ecarrinho.service.SessionService;

import com.jgoodies.forms.debug.FormDebugPanel;

/**
 * This is the main screen of the program, the start point for the user to
 * interact with the system. From here, the user can open the RegisterFram to
 * register a new account, open the LoginFrame to do log in, add a product to
 * his basket, open the basket window, BasketFrame, and open the PaymentFrame to
 * finish his order.
 * 
 * @author antonio.moreira
 */
@Component
public class MainFrame extends JFrame implements ListSelectionListener
{
	private static final long serialVersionUID = 1L;

	private DefaultListModel categoriesListModel = new DefaultListModel();
	private JList categoriesList = new JList( categoriesListModel );
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
	private SessionService sessionService;
	@Autowired
	private Adapter adapter;

	public MainFrame()
	{
		try
		{
			SwingEngine swingEngine = new SwingEngine( this );
			swingEngine.getTaglib().registerTag( "debugpanel", FormDebugPanel.class );
			add( swingEngine.render( "swixml/MainFrame.xml" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		setMinimumSize( new Dimension( 765, 550 ) );
		categoriesList.addListSelectionListener( this );
		categoriesScroll.getViewport().add( categoriesContainer.add( categoriesList ) );
	}

	/**
	 * Puts borders on the components of the screen.
	 */
	public void setBorders()
	{
		productsContainer.setBorder( BorderFactory.createLineBorder( Color.black ) );
		categoriesScroll.setBorder( BorderFactory.createLineBorder( Color.black ) );
		filterPanel.setBorder( BorderFactory.createLineBorder( Color.black ) );
		basketLoginPanel.setBorder( BorderFactory.createLineBorder( Color.black ) );
	}

	/**
	 * This method must be called before the screen is used. Its not called in
	 * the constructor because it uses variables that are wired by spring.
	 */
	public void inicialLoad()
	{
		loadCategories();
		loadProducts();
		updateUiSessionStatus();
		basketPanel.initialize();
	}

	private void loadProducts()
	{
		productsPanel.loadProducts();
		productsContainer.getViewport().add( productsPanel );
	}

	private void loadCategories()
	{
		List<Category> categories = categoryService.getAll();
		categoriesListModel.add( 0, "Todas os produtos" );
		for( Category category : categories )
		{
			categoriesListModel.addElement( category.getName() );
		}
	}

	/**
	 * This method does a information swype whenever the user logged or not. If
	 * there's a logged user, it shows a JLabel with its name a button to log
	 * out. If there is no logged user, it shows two button instead: one the
	 * bring the log in screen and another one to bring the register screen.
	 * This information is disposed on the up-right corner of the frame.
	 */
	public void updateUiSessionStatus()
	{
		if( sessionService.getLoggedUser() == null )
		{
			userName.setVisible( false );
			exitBtn.setVisible( false );
			logInBtn.setVisible( true );
			registerBtn.setVisible( true );
		}
		else
		{
			logInBtn.setVisible( false );
			registerBtn.setVisible( false );
			userName.setText( sessionService.getLoggedUser().getName() );
			userName.setVisible( true );
			exitBtn.setVisible( true );
		}
	}

	@Override
	public void valueChanged( ListSelectionEvent e )
	{
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
			loadProductsByCategory( (String) categoriesList.getSelectedValue() );
		}
		refreshUI();
	}

	private void loadProductsByCategory( String categoryName )
	{
		List<Product> products = productService.getProductsByCategory( categoryName );
		productsPanel.loadProducts( products );
	}

	public Action filter = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			productsPanel.loadProducts( productService.getProductsByDescription( filterField.getText() ) );
			refreshUI();
		}
	};

	public Action clean = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			filterField.setText( "" );
			categoriesList.setSelectedIndex( 0 );
			filterByCategory();
		}
	};

	public Action showBasket = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			adapter.callBasket( basketPanel );
		}
	};

	public Action logIn = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			setVisible( false );
			loginFrame.setVisible( true );
		}
	};

	public Action register = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			setVisible( false );
			registerFrame.setVisible( true );
		}
	};

	public Action exit = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			sessionService.LogOut();
			updateUiSessionStatus();
		}
	};

	public Action finalizeBuy = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			finalizeBuy();
		}
	};

	public void finalizeBuy()
	{
		adapter.callPayment( paymentFrame );
	}

	@Override
	public void setVisible( boolean aFlag )
	{
		if( aFlag )
		{
			updateUiSessionStatus();
		}
		super.setVisible( aFlag );
	}

	private void refreshUI()
	{
		validate();
		repaint();
	}
}