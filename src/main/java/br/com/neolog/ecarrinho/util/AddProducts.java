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
	
	Category CATEGORY_1 = new Category("CATEGORY_1");		
	
	public void productPersistence()
	{
		categoryDao.save(CATEGORY_1);
		
		for(int i = 0; i < 20; i++ )
		{
			productDao.save(new Product(CATEGORY_1, "product" + String.valueOf(i), (double) i*100, "noIcon.png"));
		}
	}
}