package br.com.neolog.ecarrinho.start;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.neolog.ecarrinho.forms.MainFrame;
import br.com.neolog.ecarrinho.util.AddProducts;

public class Start {
	
	public static final ApplicationContext contextoPrincipal = new ClassPathXmlApplicationContext("META-INF/beans.xml");
	
	public static void main(String[] args) {
		AddProducts addProd = (AddProducts) contextoPrincipal.getBean("addProducts");
		addProd.productPersistence();
		
		//ProductsForm prod = (ProductsForm) contextoPrincipal.getBean("productsFrame");
		//prod.loadAllProducts();
		//prod.setVisible(true);
		//LoginScreen login = (LoginScreen) contextoPrincipal.getBean("loginFrame");
		//login.setVisible(true);
		MainFrame main = (MainFrame) contextoPrincipal.getBean("mainFrame");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	}

}