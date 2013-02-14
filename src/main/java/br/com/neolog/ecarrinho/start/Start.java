package br.com.neolog.ecarrinho.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.neolog.ecarrinho.forms.LoginScreen;

public class Start {
	
	public static final ApplicationContext contextoPrincipal = new ClassPathXmlApplicationContext("META-INF/beans.xml");
	
	public static void main(String[] args) {
		//@SuppressWarnings("unused")
		//LoginScreen login = (LoginScreen) contextoPrincipal.getBean("loginScreen");
		new LoginScreen();
	}

}
