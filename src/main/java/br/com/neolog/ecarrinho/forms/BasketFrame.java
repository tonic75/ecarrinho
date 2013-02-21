package br.com.neolog.ecarrinho.forms;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import com.jgoodies.forms.debug.FormDebugPanel;

@Component
public class BasketFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JScrollPane productsContainer;
	
	@Autowired
	private ProductsOnBasketHolder productsOnBasketHolder;
	
	JTextField totalValue;
	
	public BasketFrame() {
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel", FormDebugPanel.class);
			add(swingEngine.render("swixml/"+this.getClass().getSimpleName()+".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(614,400);
		setResizable(false);
	}
	
	public void initialize()
	{
		productsContainer.getViewport().add(productsOnBasketHolder); 
		productsOnBasketHolder.setTotalValue(totalValue);
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		productsOnBasketHolder.refreshBasket();
		super.setVisible(aFlag);
	}
	
	
}
