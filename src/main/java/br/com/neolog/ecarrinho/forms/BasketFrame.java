package br.com.neolog.ecarrinho.forms;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import com.jgoodies.forms.debug.FormDebugPanel;

/**
 * This is the frame that shows to the user his products, prices and total of
 * the order so far. It can be opened without problem at the same time of the
 * main screen.
 * 
 * @author antonio.moreira
 */
@Component
public class BasketFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JScrollPane productsContainer;

	@Autowired
	private ProductsOnBasketHolder productsOnBasketHolder;

	private JTextField totalValue;

	public BasketFrame()
	{
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
		setSize( 614, 410 );
		setResizable( false );
	}

	/**
	 * This method must be called before the screen is used. Its not called in
	 * the constructor because it uses variables that are wired by spring.
	 */
	public void initialize()
	{
		productsContainer.getViewport().add( productsOnBasketHolder );
		productsOnBasketHolder.setTotalValue( totalValue );
	}

	@Override
	public void setVisible( boolean aFlag )
	{
		if( aFlag )
		{
			productsOnBasketHolder.refreshBasket();
		}
		super.setVisible( aFlag );
	}
}