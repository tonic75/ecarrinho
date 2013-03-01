package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.util.NumberField;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;

/**
 * This is a little panel that has the information of a single product in the
 * user's basket. It displays the product's description, amount ordered and
 * unit's price. Besides, there are also two buttons: one to alter the amount
 * ordered and another one to take the product of the basket. It has a reference
 * to the panel that holds all the products on the basket,
 * ProductsOnBasketHolder, because that class has a autowired reference to the
 * service that can do the remove and alter amount operations.
 * 
 * @author antonio.moreira
 */
public class ProductOnBasketPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private ProductsOnBasketHolder productsOnBasketHolder;
	private Product product;
	private Long amount;

	private CellConstraints cc = new CellConstraints();

	private JLabel productName;
	private NumberField amountField = new NumberField();
	private JTextField unitPrice;

	private JPanel mainPanel;

	public ProductOnBasketPanel( Product product, Long amount, ProductsOnBasketHolder productsOnBasketHolder )
	{
		this.productsOnBasketHolder = productsOnBasketHolder;
		this.product = product;
		this.amount = amount;

		try
		{
			SwingEngine swingEngine = new SwingEngine( this );
			swingEngine.getTaglib().registerTag( "debugpanel", FormDebugPanel.class );
			add( swingEngine.render( "swixml/" + this.getClass().getSimpleName() + ".xml" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		mainPanel.add( amountField, cc.xy( 4, 1 ) );
		setInfo();
	}

	/**
	 * Puts the product information into the respective fields.
	 */
	private void setInfo()
	{
		productName.setText( product.getDescription() );
		amountField.setText( String.valueOf( amount ) );
		unitPrice.setText( String.valueOf( product.getPrice() ) );
	}

	public Action changeAmount = new AbstractAction()
	{
		private static final long serialVersionUID = -3163216371714766293L;

		public void actionPerformed( ActionEvent e )
		{
			if( Long.valueOf( amountField.getText() ) > 0 )
			{
				amount = Long.valueOf( amountField.getText() );
				productsOnBasketHolder.changeAmount( product, amount );
				productsOnBasketHolder.refreshBasket();
			}
		}
	};

	public Action remove = new AbstractAction()
	{
		private static final long serialVersionUID = -5875894790824308012L;

		public void actionPerformed( ActionEvent e )
		{
			productsOnBasketHolder.remove( product );
			productsOnBasketHolder.refreshBasket();
		}
	};
}
