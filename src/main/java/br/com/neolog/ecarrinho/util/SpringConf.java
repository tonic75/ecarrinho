package br.com.neolog.ecarrinho.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.dao.ProductDAO;
import br.com.neolog.ecarrinho.dao.UserDAO;

@Configuration
public class SpringConf {
	
	@Bean
	public ProductDAO productDAO()
	{
		return new ProductDAO();
	}
	
	@Bean
	public UserDAO userDAO()
	{
		return new UserDAO();
	}
	
	@Bean
	public User user( UserDAO userDAO )
	{
		return new User(userDAO);
	}
}
