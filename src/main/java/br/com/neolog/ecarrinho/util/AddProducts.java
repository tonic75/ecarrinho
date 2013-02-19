package br.com.neolog.ecarrinho.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.CategoryDao;
import br.com.neolog.ecarrinho.dao.ProductDao;

@Component
public class AddProducts {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
		
	
	public void productPersistence()
	{
		Category CATEGORY_0 = new Category("CATEGORY_0");	
		
		categoryDao.save(CATEGORY_0);
		for(int i = 1; i < 5; i++ )
		{
			categoryDao.save(new Category("category " + String.valueOf(i)));
		}
		
		for(int i = 0; i < 20; i++ )
		{
		//	productDao.save(new Product(CATEGORY_0, "product" + String.valueOf(i), (double) i*100, "noIcon.png"));
		}
	}
}