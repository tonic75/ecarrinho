package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.start.Start;

public class LoginScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField userText;
	private JPasswordField passText;

	public LoginScreen() {

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
			User user = (User) Start.contextoPrincipal.getBean("user");
			user.setUser(userText.getText());
//			char[] passChar = passText.getPassword().toString();
//			if(  )
			user.setPass(String.valueOf(passText.getPassword()));
			if( user.isValidUser() )
			{
				System.out.println("usuario valido");
			}
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
