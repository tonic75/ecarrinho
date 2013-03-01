package br.com.neolog.ecarrinho.forms;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swixml.SwingEngine;

import br.com.neolog.ecarrinho.bean.PaymentMethod;
import br.com.neolog.ecarrinho.bean.UserOrder;
import br.com.neolog.ecarrinho.forms.adapters.Adapter;
import br.com.neolog.ecarrinho.service.BasketService;
import br.com.neolog.ecarrinho.service.Service;
import br.com.neolog.ecarrinho.service.SessionService;
import br.com.neolog.ecarrinho.service.UserOrderService;
import br.com.neolog.services.WS.WS;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;

/**
 * This is the frame where the user puts his final information for closing the
 * other. Here he can chose his payment method, and change his registered
 * information, like address, card number etc. For the payment confirmation, it
 * calls methods from a remote service.
 * 
 * @author antonio.moreira
 */
@Component
public class PaymentFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	@Autowired
	private BasketService basketService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private MainFrame mainFrame;
	@Autowired
	private Service creditService;
	@Autowired
	private WS bankSlipService;
	@Autowired
	private Adapter adapter;

	private JPanel ousidePanel;
	private JTextField totalField;
	private JTextArea addressArea;
	private JComboBox paymentMethods = new JComboBox( PaymentMethod.values() );
	private JLabel cardAgencyLabel;
	private JTextField cardField;
	private JTextField agencyField;
	private JLabel accLabel;
	private JTextField accField;

	private JFrame paymentWaitFrame = new JFrame( "Esperando" );

	CellConstraints cc = new CellConstraints();

	public PaymentFrame()
	{
		try
		{
			SwingEngine swingEngine = new SwingEngine( this );
			swingEngine.getTaglib().registerTag( "debugpanel", FormDebugPanel.class );
			add( swingEngine.render( "swixml/PaymentFrame.xml" ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		setResizable( false );
		setSize( 288, 245 );

		paymentWaitFrame.add( new JLabel( "Esperando pela confirmação do seu pagamento" ) );
		paymentWaitFrame.setSize( 250, 100 );
		paymentWaitFrame.setResizable( false );
		paymentWaitFrame.setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );

		ousidePanel.add( paymentMethods, cc.xy( 3, 6 ) );

		addressArea.setBorder( BorderFactory.createLineBorder( Color.black, 1 ) );

		paymentMethods.addItemListener( paymentListener );

		addWindowListener( new WindowAdapter()
		{
			@Override
			public void windowClosing( WindowEvent e )
			{
				mainFrame.setVisible( true );
			}
		} );
	}

	/**
	 * This keeps listening when the user changes his payment method to organize
	 * the screen for his decision.
	 */
	public ItemListener paymentListener = new ItemListener()
	{
		public void itemStateChanged( ItemEvent e )
		{
			switch( paymentMethods.getSelectedIndex() )
			{
			case 0:
				organizeForCard();
				break;
			case 1:
				organizeForBaskSlip();
				break;
			case 2:
				organizeForDebt();
				break;
			}
			fillFields();
		}
	};

	/**
	 * Organize the fields for the user to input his information for credit card
	 * payment.
	 */
	private void organizeForCard()
	{
		cleanThePaymentMethods();
		cardAgencyLabel.setText( "Cartão:" );
		cardField.setVisible( true );
	}

	/**
	 * Organize the fields for the user to input his information for BaskSlip
	 * payment.
	 */
	private void organizeForBaskSlip()
	{
		cleanThePaymentMethods();
	}

	/**
	 * Organize the fields for the user to input his information for debt into
	 * account payment.
	 */
	private void organizeForDebt()
	{
		cleanThePaymentMethods();
		cardAgencyLabel.setText( "Agência:" );
		agencyField.setVisible( true );
		accLabel.setVisible( true );
		accField.setVisible( true );
	}

	/**
	 * Clean all the fields relative to payment,
	 */
	private void cleanThePaymentMethods()
	{
		cardAgencyLabel.setText( "" );
		cardField.setText( "" );
		cardField.setVisible( false );
		agencyField.setText( "" );
		agencyField.setVisible( false );
		accLabel.setVisible( false );
		accField.setVisible( false );
	}

	/**
	 * Fills all the user information fields with the data he provided at
	 * register time.
	 */
	private void fillFields()
	{
		totalField.setText( "R$ " + basketService.getTotalValue() );
		addressArea.setText( sessionService.getLoggedUser().getAddress() );
		cardField.setText( sessionService.getLoggedUser().getCard() );
		agencyField.setText( sessionService.getLoggedUser().getAgency() );
		accField.setText( sessionService.getLoggedUser().getAcc() );
	}

	public Action confirm = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			new Worker().execute();
		}
	};

	/**
	 * This SwingWorker is called when the user tries to finish his payment in
	 * order not to freeze the screen while it waits for the remote service to
	 * confirm the user payment. It shows the waiting screen and hides it after
	 * the payment gets confirmed. If it happens to payment not be confirmed, a
	 * message is shown to the user.
	 * 
	 * @author antonio.moreira
	 */
	private class Worker extends SwingWorker<Void, Void>
	{
		@Override
		protected Void doInBackground() throws Exception
		{
			showWait();
			switch( paymentMethods.getSelectedIndex() )
			{
			case 0:
				if( creditService.isCreditOk( cardField.getText() ) )
				{
					saveOrder();
				}
				else
				{
					showPaymentProblemOptionPane();
				}
				break;
			case 1:
				if( bankSlipService.isBaskSlipOk( sessionService.getLoggedUser().getName() ) )
				{
					saveOrder();
				}
				else
				{
					showPaymentProblemOptionPane();
				}
				break;
			case 2:
				if( creditService.isDebtOk( agencyField.getText(), accField.getText() ) )
				{
					saveOrder();
				}
				else
				{
					showPaymentProblemOptionPane();
				}
				break;
			}
			return null;
		}

		@Override
		protected void done()
		{
			hideWait();
			try
			{
				get();
			}
			catch( InterruptedException e )
			{
				e.printStackTrace();
			}
			catch( ExecutionException e )
			{
				e.printStackTrace();
			}
		}

	}

	public void showPaymentProblemOptionPane()
	{
		JOptionPane.showMessageDialog( getParent(), "Problemas relacionados ao pagamento", "", JOptionPane.ERROR_MESSAGE );
	}

	private void showWait()
	{
		setVisible( false );
		paymentWaitFrame.setVisible( true );
	}

	private void hideWait()
	{
		paymentWaitFrame.setVisible( false );
		printSuccessMsg();
		backToMain();
	}

	/**
	 * Uses the UserOrderService to save the client's order.
	 */
	public void saveOrder()
	{
		try
		{
			userOrderService
					.save( new UserOrder( basketService.getBasket(), Calendar.getInstance(), sessionService.getLoggedUser(), addressArea.getText(),
							cardField.getText(), agencyField.getText(), accField.getText(), (PaymentMethod) ( paymentMethods.getSelectedItem() ) ) );
			basketService.newBasket();
		}
		catch( HibernateException e1 )
		{
			e1.printStackTrace();
		}
	}

	public void printSuccessMsg()
	{
		JOptionPane.showMessageDialog( getParent(), "Compra efetuada com sucesso!", "", JOptionPane.INFORMATION_MESSAGE );
	}

	public Action cancel = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		public void actionPerformed( ActionEvent e )
		{
			backToMain();
		}
	};

	public void backToMain()
	{
		setVisible( false );
		mainFrame.setVisible( true );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Window#setVisible(boolean)
	 */
	@Override
	public void setVisible( boolean aFlag )
	{
		if( aFlag )
		{
			paymentListener.itemStateChanged( null );
		}
		super.setVisible( aFlag );
	}
}