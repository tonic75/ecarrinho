package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.swixml.SwingEngine;


import br.com.neolog.ecarrinho.bean.Product;

import com.jgoodies.forms.debug.FormDebugPanel;

/**
 * The Class ProductSpot.
 * 
 * @author antonio.moreira
 */

public class ProductSpot extends JPanel {
	private static final long serialVersionUID = 1L;

	private Product product;

	private JTextField qtd;

	private JLabel price;

	private JTextArea description;

	private JLabel iconLabel;
	
	private ProductsPanel productsPanel;

	/**
	 * Instantiates a new product spot.
	 * 
	 * @param product
	 *            the product to be showed in this spot
	 */
	public ProductSpot(Product product, ProductsPanel productsPanel ) {
		this.product = product;
		this.productsPanel = productsPanel; 

		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel",
					FormDebugPanel.class);
			add(swingEngine.render("swixml/" + this.getClass().getSimpleName()
					+ ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadProductData();
	}

	/** The action of the button that add a product to the basket. */
	// TODO: complete it when it gets implemented
	public Action addToBasket = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			if( Long.valueOf(qtd.getText()) > 0 ) 
			{
				productsPanel.addToBasket(product, Long.valueOf(qtd.getText()));
				// TODO: verify if that are enough stock to execute the action
			}
		}
	};

	/**
	 * Load product data to the swing components.
	 */
	private void loadProductData() {
		price.setText("Preço: R$" + product.getPrice().toString());
		description.setText(product.getDescription());
		iconLabel.setIcon(new ImageIcon(new ImageIcon(Product.ICON_PATH
				+ product.getIconName()).getImage().getScaledInstance(128, 128,
				java.awt.Image.SCALE_AREA_AVERAGING)));
	}
}
