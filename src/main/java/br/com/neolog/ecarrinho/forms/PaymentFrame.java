package br.com.neolog.ecarrinho.forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.PaymentMethod;
import br.com.neolog.ecarrinho.bean.UserOrder;
import br.com.neolog.ecarrinho.service.BasketService;
import br.com.neolog.ecarrinho.service.SessionService;
import br.com.neolog.ecarrinho.service.UserOrderService;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;

@Component
public class PaymentFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	@Autowired
	BasketService basketService;
	@Autowired
	SessionService session;
	@Autowired
	UserOrderService userOrderService;
	@Autowired
	MainFrame mainFrame;

	private JPanel ousidePanel;
	private JTextField totalField;
	private JTextArea addressArea;
	private JComboBox paymentMethods = new JComboBox(PaymentMethod.values());
	private JLabel cardAgencyLabel;
	private JTextField cardField;
	private JTextField agencyField;
	private JLabel accLabel;
	private JTextField accField;

	CellConstraints cc = new CellConstraints();

	public PaymentFrame() {
		try {
			SwingEngine swingEngine = new SwingEngine(this);
			swingEngine.getTaglib().registerTag("debugpanel",
					FormDebugPanel.class);
			add(swingEngine.render("swixml/PaymentFrame.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ousidePanel.add(paymentMethods, cc.xy(3, 6));
		addressArea.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		setSize(288, 245);
		paymentMethods.addItemListener(paymentListener);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				mainFrame.setVisible(true);
			}
		});
	}

	public ItemListener paymentListener = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			switch (paymentMethods.getSelectedIndex()) {
			case 0:
				organizeForCard();
				break;
			case 1:
				organizeForBolet();
				break;
			case 2:
				organizeForDebit();
				break;
			}
			fillFields();
		}
	};

	private void organizeForCard() {
		cleanThePaymentMethods();
		cardAgencyLabel.setText("Cartão:");
		cardField.setVisible(true);
	}

	private void organizeForBolet() {
		cleanThePaymentMethods();
	}

	private void organizeForDebit() {
		cleanThePaymentMethods();
		cardAgencyLabel.setText("Agência:");
		agencyField.setVisible(true);
		accLabel.setVisible(true);
		accField.setVisible(true);
	}

	private void cleanThePaymentMethods() {
		cardAgencyLabel.setText("");
		cardField.setText("");
		cardField.setVisible(false);
		agencyField.setText("");
		agencyField.setVisible(false);
		accLabel.setVisible(false);
		accField.setVisible(false);
	}

	private void fillFields() {
		totalField.setText("R$ " + basketService.getTotalValue());
		addressArea.setText(session.getLoggedUser().getAddress());
		cardField.setText(session.getLoggedUser().getCard());
		agencyField.setText(session.getLoggedUser().getAgency());
		accField.setText(session.getLoggedUser().getAcc());
	}

	public Action confirm = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			try
			{
				userOrderService.save(new UserOrder(basketService.getBasket(),
						Calendar.getInstance(), session.getLoggedUser(),
						addressArea.getText(), cardField.getText(),
						agencyField.getText(), accField.getText(),
						(PaymentMethod)(paymentMethods.getSelectedItem())));
			}
			catch( HibernateException e1 )
			{
				e1.printStackTrace();
			}
		}
	};

	public Action cancel = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			mainFrame.setVisible(true);
		}
	};

	@Override
	public void setVisible(boolean aFlag) {
		paymentListener.itemStateChanged(null);
		super.setVisible(aFlag);
	}
}