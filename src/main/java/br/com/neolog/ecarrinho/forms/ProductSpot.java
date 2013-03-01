package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.util.NumberField;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;

/**
 * The Class ProductSpot.
 * 
 * @author antonio.moreira
 */

public class ProductSpot extends JPanel {
	private static final long serialVersionUID = 1L;

	private Product product;
	private NumberField amount = new NumberField();
	private JLabel price;
	private JTextArea description;
	private JLabel iconLabel;
	private ProductsPanel productsPanel;
	private JPanel addPanel;
	private CellConstraints cc = new CellConstraints();

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
		amount.setText("1");
		loadProductData();
		
		addPanel.add(amount, cc.xy(2, 1));
	}

	/** The action of the button that add a product to the basket. */
	public Action addToBasket = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			if( !amount.getText().equals("") && Long.valueOf(amount.getText()) > 0 ) 
			{
				productsPanel.addToBasket(product, Long.valueOf(amount.getText()));
			}
			else
			{
				JOptionPane.showMessageDialog(null,
						"Quantidade inválida!", "",
						JOptionPane.ERROR_MESSAGE);
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
