package br.com.neolog.ecarrinho.util;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.CategoryDao;
import br.com.neolog.ecarrinho.dao.ProductDao;

import com.google.common.collect.ImmutableList;

@Component
public class AddProducts {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	Category CATEGORY_1 = new Category("CATEGORY_1");
	Category CATEGORY_2 = new Category("CATEGORY_2");
	
	Product PRODUCT_1 = new Product(CATEGORY_1, "description1", 100.100, "noImage.png");
	Product PRODUCT_2 = new Product(CATEGORY_1, "description2", 200.200, "noImage.png");
	Product PRODUCT_3 = new Product(CATEGORY_2, "description3", 300.300, "noImage.png");
	Product PRODUCT_4 = new Product(CATEGORY_1, "description4", 400.400, "noImage.png");
	Product PRODUCT_5 = new Product(CATEGORY_2, "description5", 500.500, "noImage.png");
	
	List<Category> categories = new ImmutableList.Builder<Category>()
			.add(CATEGORY_1)
			.add(CATEGORY_2)
			.build();
	
	List<Product> products = new ImmutableList.Builder<Product>()
		.add(PRODUCT_1)
		.add(PRODUCT_2)
		.add(PRODUCT_3)
		.add(PRODUCT_4)
		.add(PRODUCT_5)
		.build();
	
	public void productPersistence()
	{
		categoryDao.save(CATEGORY_1);
		categoryDao.save(CATEGORY_2);
		
		productDao.save(PRODUCT_1);
		productDao.save(PRODUCT_2);
		productDao.save(PRODUCT_3);
		productDao.save(PRODUCT_4);
		productDao.save(PRODUCT_5);
		
	}
}