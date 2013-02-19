package br.com.neolog.ecarrinho.forms;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.Product;

import com.jgoodies.forms.debug.FormDebugPanel;

/**
 * 
 * 
 * @author antonio.moreira
 *
 */

public class ProductSpot extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Product product;
	
	private JTextField qtd;
	private JLabel price;
	private JLabel description;
	private JLabel iconLabel;

	public ProductSpot( Product product ) throws HeadlessException {
		this.product = product;
		
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel", FormDebugPanel.class);
			add(swingEngine.render("swixml/"+this.getClass().getSimpleName()+".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(600,200);
		loadProductData();
	}
	
	public Action addToBasket = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			System.out.println("adicionar ao carrinho");
			System.out.println(qtd.getText());
			iconLabel.setText("text");
		}
	};
	
	public void loadProductData()
	{
		price.setText(product.getPrice().toString());
		description.setText(product.getDescription());
		iconLabel.setIcon(new ImageIcon(Product.ICON_PATH + product.getIconName()));
	}
}
