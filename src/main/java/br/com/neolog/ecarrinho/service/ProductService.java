package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.ProductDao;

@Component
public class ProductService {
	
	@Autowired
	ProductDao dao;
	
	public List<Product> getAllProducts()
	{
		return dao.getAll();
	}

}
