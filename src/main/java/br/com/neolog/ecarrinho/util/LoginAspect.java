package br.com.neolog.ecarrinho.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.ecarrinho.forms.LoginFrame;
import br.com.neolog.ecarrinho.forms.PaymentFrame;
import br.com.neolog.ecarrinho.service.SessionService;

@Aspect
public class LoginAspect {
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private LoginFrame loginFrame;
	
	@Autowired
	private PaymentFrame paymentFrame;
	
	@Before("execution( * br.com.neolog.ecarrinho.service.SessionService.getLoggedUser(..))")
	public void logginCheck()
	{
		if( !sessionService.isAnyoneLogged() )
		{
			loginFrame.setVisible(true);
		}
	}	
}
