package br.com.neolog.ecarrinho.util;

import javax.swing.JOptionPane;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.forms.LoginFrame;
import br.com.neolog.ecarrinho.forms.PaymentFrame;
import br.com.neolog.ecarrinho.service.BasketService;
import br.com.neolog.ecarrinho.service.SessionService;

@Aspect
@Component
public class LoginAspect {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private LoginFrame loginFrame;

	@Autowired
	private PaymentFrame paymentFrame;
	
	@Autowired
	private BasketService basketService;

	@Around("@annotation(MustBeLogged)")
	public void logginCheck( ProceedingJoinPoint join ) {
		if (!sessionService.isAnyoneLogged()) 
		{
			loginFrame.setVisible(true);
		}
		else
		{
			basketCheck(join);
		}
	}

	@Around("@annotation(NotEmptyBasket)")
	public void basketCheck( ProceedingJoinPoint join ) {
		if( basketService.isEmpty() )
		{
			JOptionPane.showMessageDialog(null, "Carrinho vazio", "",
				JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			try {
				join.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
}
