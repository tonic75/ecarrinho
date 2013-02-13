package br.com.neolog.ecarrinho.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.ProductDAO;
import br.com.neolog.ecarrinho.forms.LoginScreen;

@Configuration
public class SpringConf {
	
	@Bean
	public ProductDAO productDAO()
	{
		return new ProductDAO();
	}
	
	@Bean
	public LoginScreen loginScreen( ProductDAO productDAO )
	{
		return new LoginScreen(productDAO);
	}

}
