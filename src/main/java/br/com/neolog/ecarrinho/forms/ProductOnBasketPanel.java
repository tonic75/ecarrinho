package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.swixml.SwingEngine;


import br.com.neolog.ecarrinho.bean.Product;

import com.jgoodies.forms.debug.FormDebugPanel;

public class ProductOnBasketPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ProductsOnBasketHolder productsOnBasketHolder;
	private Product product;
	private Long amount;
	
	private JLabel productName;
	private JTextField prodQtdTxt;
	private JTextField unitPrice;

	public ProductOnBasketPanel( Product product, Long amount, ProductsOnBasketHolder productsOnBasketHolder ) {
		this.productsOnBasketHolder = productsOnBasketHolder;
		this.product = product;
		this.amount = amount;
		
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel",
					FormDebugPanel.class);
			add(swingEngine.render("swixml/" + this.getClass().getSimpleName()
					+ ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setInfo();
	}	
	
	private void setInfo()
	{
		productName.setText(product.getDescription());
		prodQtdTxt.setText(String.valueOf(amount));
		unitPrice.setText(String.valueOf(product.getPrice()));
	}
	
	public Action changeAmount = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			// TODO: when it gets implemented, verify if we have enough stock
			if( Long.valueOf(prodQtdTxt.getText()) > 0 )
			{
				amount = Long.valueOf(prodQtdTxt.getText());
				productsOnBasketHolder.changeAmount(product,amount);
				productsOnBasketHolder.refreshBasket();
			}
		}
	};
	
	public Action remove = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			// TODO: put the product back in stock
			productsOnBasketHolder.remove(product);
			productsOnBasketHolder.refreshBasket();
		}
	};
}
