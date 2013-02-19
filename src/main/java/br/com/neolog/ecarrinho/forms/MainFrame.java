package br.com.neolog.ecarrinho.forms;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import com.jgoodies.forms.debug.FormDebugPanel;

@Component
public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel productsPanelOut;
	@Autowired
	ProductsPanel ProductsPanelIn;

	public MainFrame()
	{
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel", FormDebugPanel.class);
			add(swingEngine.render("swixml/"+this.getClass().getSimpleName()+".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(800,500);
		productsPanelOut = ProductsPanelIn;  
	}

}
