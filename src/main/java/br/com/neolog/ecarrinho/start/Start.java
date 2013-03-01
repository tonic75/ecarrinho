package br.com.neolog.ecarrinho.start;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.neolog.ecarrinho.forms.MainFrame;

/**
 * A class to get the main frame of the program and start it.
 * 
 * @author antonio.moreira
 */
public class Start {
	
	public static final ApplicationContext contextoPrincipal = new ClassPathXmlApplicationContext("META-INF/beans.xml");
	
	public static void main(String[] args) {
		AddInitialData addProd = (AddInitialData) contextoPrincipal.getBean("addInitialData");
		addProd.productPersistence();
	
		MainFrame main = (MainFrame) contextoPrincipal.getBean("mainFrame");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.inicialLoad();
		main.setVisible(true);
	}

}