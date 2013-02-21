package br.com.neolog.ecarrinho.forms;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.service.UserService;

import com.jgoodies.forms.debug.FormDebugPanel;

@Component
public class RegisterFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	@Autowired
	UserService userService;
	private JTextField nameField;
	private JTextField adsressField;
	private JTextField cpfField;
	private JTextField cardField;
	private JTextField userField;
	private JPasswordField passField;

	public RegisterFrame() {
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel",
					FormDebugPanel.class);
			add(swingEngine.render("swixml/" + this.getClass().getSimpleName()
					+ ".xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		setSize(276, 217);
	}

	public Action register = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			try {
				userService.registerUser(new User(userField.getText(), String
						.valueOf(passField.getPassword()), nameField.getText(),
						adsressField.getText(), cardField.getText(), cpfField
								.getText()));

				JOptionPane.showMessageDialog(getParent(),
						"Usuário cadastrado", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(getParent(),
						"Dados de usuário incompletos", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (JpaSystemException e2) {
				JOptionPane.showMessageDialog(getParent(),
						"Nome de usuário não está disponível", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	public Action cancel = new AbstractAction() {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	};
	
	@Override
	public void setVisible( boolean aFlag )
	{
		cleanFields();
		super.setVisible(aFlag);
	}
	
	public void cleanFields()
	{
		nameField.setText("");
		adsressField.setText("");
		cpfField.setText("");
		cardField.setText("");
		userField.setText("");
		passField.setText("");
	}
}
