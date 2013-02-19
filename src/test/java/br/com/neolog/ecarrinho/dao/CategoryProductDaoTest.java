package br.com.neolog.ecarrinho.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/beans.xml" })
public class CategoryProductDaoTest {

	@Autowired
	ProductDao productDao;

	@Autowired
	CategoryDao categoryDao;

	Category CATEGORY_1 = new Category("CATEGORY_1");
	Category CATEGORY_2 = new Category("CATEGORY_2");

	Product PRODUCT_1 = new Product(CATEGORY_1, "description1", 100.100, "noIcon.png");
	Product PRODUCT_2 = new Product(CATEGORY_1, "description2", 200.200, "noIcon.png");
	Product PRODUCT_3 = new Product(CATEGORY_2, "description3", 300.300, "noIcon.png");
	Product PRODUCT_4 = new Product(CATEGORY_1, "description4", 400.400, "noIcon.png");
	Product PRODUCT_5 = new Product(CATEGORY_2, "description5", 500.500, "noIcon.png");

	List<Category> categories = new ImmutableList.Builder<Category>()
			.add(CATEGORY_1).add(CATEGORY_2).build();

	List<Product> products = new ImmutableList.Builder<Product>()
			.add(PRODUCT_1)
			.add(PRODUCT_2)
			.add(PRODUCT_3)
			.add(PRODUCT_4)
			.add(PRODUCT_5)
			.build();

	List<Product> productsInCategory1 = new ImmutableList.Builder<Product>()
			.add(PRODUCT_1)
			.add(PRODUCT_2)
			.add(PRODUCT_4)
			.build();
	
	List<Product> productsInCategory2 = new ImmutableList.Builder<Product>()
			.add(PRODUCT_3)
			.add(PRODUCT_5)
			.build();

	@Test
	public void productPersistence() {
		categoryDao.save(CATEGORY_1);
		categoryDao.save(CATEGORY_2);

		productDao.save(PRODUCT_1);
		productDao.save(PRODUCT_2);
		productDao.save(PRODUCT_3);
		productDao.save(PRODUCT_4);
		productDao.save(PRODUCT_5);

		List<Category> categoriesFromDB = categoryDao.getAll();
		Assert.assertEquals(2, categoriesFromDB.size());

		Assert.assertEquals(categories.toString(), categoriesFromDB.toString());

		List<Product> productsFromDB = productDao.getAll();
		Assert.assertEquals(5, productsFromDB.size());

		Assert.assertEquals(products.toString(), productsFromDB.toString());

		Assert.assertEquals(categoryDao.get(CATEGORY_1.getName()).toString(),
				CATEGORY_1.toString());
		Assert.assertEquals(productDao.get(PRODUCT_4.getId()).toString(),
				PRODUCT_4.toString());
		
		//Now testing the function that gets all from each category
		List<Product> productsFromCategory1 = productDao.getProductsByCategory(CATEGORY_1);
		Assert.assertEquals(productsInCategory1, productsFromCategory1);
		
		List<Product> productsFromCategory2 = productDao.getProductsByCategory(CATEGORY_2);
		Assert.assertEquals(productsInCategory2, productsFromCategory2);
		
		//Now the product delete function
		productDao.delete(PRODUCT_3);
		productsFromDB = productDao.getAll();
		Assert.assertEquals(4, productsFromDB.size());
	}
}
