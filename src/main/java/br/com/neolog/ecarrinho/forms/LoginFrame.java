package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.service.SessionService;
import br.com.neolog.ecarrinho.service.UserService;

/**
 * The Class LoginFrame. This is a frame for a user to log in, including fields
 * for name and password, a button to login e another on to register a new user.
 * 
 * @author antonio.moreira
 */
@Component
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisterFrame registerFrame;
	
	@Autowired
	private SessionService session;
	
	@Autowired
	private MainFrame mainFrame;

	private JTextField userText;

	private JPasswordField passText;

	public LoginFrame() {
		setSize(270, 150);
		try {
			SwingEngine engine = new SwingEngine(this);
			add(engine.render("swixml/" + this.getClass().getSimpleName()
					+ ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				mainFrame.setVisible(true);
			}
		});
	}

	/**
	 * Captures do action of log in and asks to the userService if the user and
	 * pass passed are right.
	 */
	public Action logIn = new AbstractAction() {
		private static final long serialVersionUID = -168221978922967631L;
		public void actionPerformed(ActionEvent e) {
			if((userService.isValidPassword(userText.getText(),
					String.valueOf(passText.getPassword()))))
			{
				session.logIn(userService.getUser(userText.getText()));
				JOptionPane.showMessageDialog(getParent(),
						"Bem vindo, " + userText.getText(), ":)",
						JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				mainFrame.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(getParent(),
						"Dados incorretos", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	/**
	 * Captures the action of registering a new user and calls the frame that
	 * does it.
	 */
	public Action newUser = new AbstractAction() {
		private static final long serialVersionUID = -9198682512676260864L;

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			registerFrame.setVisible(true);
		}
	};
	
	
	/* (non-Javadoc)
	 * @see java.awt.Window#setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean aFlag )
	{
		super.setVisible(aFlag);
	}
	
	

}
