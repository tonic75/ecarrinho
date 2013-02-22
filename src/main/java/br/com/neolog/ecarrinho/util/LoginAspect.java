package br.com.neolog.ecarrinho.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.ecarrinho.forms.LoginFrame;
import br.com.neolog.ecarrinho.forms.MainFrame;
import br.com.neolog.ecarrinho.service.SessionService;

@Aspect
public class LoginAspect {
	
	@Autowired
	private SessionService sessionService;
//	
//	@Autowired
//	private MainFrame mainFrame;
//	
	@Autowired
	private LoginFrame loginFrame;
	
//	@Pointcut( "execution( * br.com.neolog.ecarrinho.util.JFrameAspectAdapter.windowCaller(..))")
//	public void loginCut() {}
	
	@Before("execution( * br.com.neolog.ecarrinho.util.JFrameAspectAdapter.windowCaller(..))")
	public void logginCheck()
	{
		if( !sessionService.isAnyoneLogged() )
		{
			loginFrame.setVisible(true);
		}			
	}	
}
