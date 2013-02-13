package br.com.neolog.ecarrinho.forms;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.ProductDAO;



public class LoginScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ProductDAO productDAO;
	
	
	/** submit counter */
	private int clicks;

	/**
	 * JTextField member gets instantiated through Swixml (look for id="tf" in
	 * xml descriptor)
	 */
	public JTextField tf;

	/** Jlabel to display number of button clicks */
	public JLabel cnt;
	
	
	
	public LoginScreen(final ProductDAO productDAO) {
		this.productDAO = productDAO;
		
		try {
			new SwingEngine( this ).render( "swixml/LoginScreen.xml" ).setVisible( true );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,300);
		
		this.setLayout(new FlowLayout());
		
		JButton salvar = new JButton("salvar");
		salvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Product prod = new Product("produto1", "descrição 1", 500.5);
				productDAO.save(prod);
			}
		});
		
		add(salvar);
		
		setVisible(true);	*/
	}

}
