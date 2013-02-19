package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.service.UserService;

@Component
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	private JTextField userText;
	private JPasswordField passText;
	
	public LoginFrame() {
		setSize(270,150);
		try {
			SwingEngine engine = new SwingEngine(this);
			add(engine.render("swixml/"+this.getClass().getSimpleName()+".xml"));
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
			System.out.println(userService.isValidPassword(userText.getText(), String.valueOf(passText.getPassword())));
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
