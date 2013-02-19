package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.CategoryDao;
import br.com.neolog.ecarrinho.dao.ProductDao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

@Component
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	public List<Product> getAllProducts()
	{
		return productDao.getAll();
	}

	public ArrayListMultimap<Category, Product> getAllProductsByCategory()
	{
		Multimap<Category, Product> map = ArrayListMultimap.create();
		
		List<Category> categories = categoryDao.getAll();
		for (Category category : categories) {
			List<Product> products = productDao.getProductsByCategory(category);
			for (Product product : products) {
				map.put(category, product);
			}
		}
		
		return null;
	}
}
