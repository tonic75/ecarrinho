package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
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

	private JTextField user;
	private JTextField pass;

	public LoginScreen(final ProductDAO productDAO) {
		this.productDAO = productDAO;

		try {
			new SwingEngine(this).render("swixml/LoginScreen.xml").setVisible(
					true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Action logIn = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -168221978922967631L;

		public void actionPerformed(ActionEvent e) {
				
		}
	};
	
	public Action newUser = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -9198682512676260864L;

		public void actionPerformed(ActionEvent e) {
			System.out.println("ahala papiu");
		}
	};

}
