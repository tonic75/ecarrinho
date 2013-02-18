package br.com.neolog.ecarrinho.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.neolog.ecarrinho.forms.ProductsForm;
import br.com.neolog.ecarrinho.util.AddProducts;

public class Start {
	
	public static final ApplicationContext contextoPrincipal = new ClassPathXmlApplicationContext("META-INF/beans.xml");
	
	public static void main(String[] args) {
		AddProducts addProd = (AddProducts) contextoPrincipal.getBean("addProducts");
		addProd.productPersistence();
		
		ProductsForm prod = (ProductsForm) contextoPrincipal.getBean("productsForm");
		prod.loadAllProducts();
	}

}
