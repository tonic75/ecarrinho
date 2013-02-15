package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.persistence.NoResultException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.dao.UserDao;

@Component
public class LoginScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDao dao;
	
	private JTextField userText;
	private JPasswordField passText;
	
	public LoginScreen() {
		System.out.println(dao);

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
			try
			{
				System.out.println("dao");
				User user = dao.getUserbyUserName(userText.getText());
				if( user.isValidPassword(String.valueOf(passText.getPassword())) )
				{
					System.out.println("usuario valido");
				}
				else
				{
					System.out.println("senha errada");
				}
			}
			catch( NoResultException e1 )
			{
				System.out.println("usuario nao encontrado");
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
