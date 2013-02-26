package br.com.neolog.ecarrinho.dao;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.ecarrinho.bean.Acquisition;
import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.PaymentMethod;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.bean.User;
import br.com.neolog.ecarrinho.bean.UserOrder;
import br.com.neolog.ecarrinho.service.AcquisitionService;
import br.com.neolog.ecarrinho.service.BasketService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/beans.xml" })
public class StockTest {
	
	@Autowired
	AcquisitionDao acquisitionDao;
	
	@Autowired
	AcquisitionService acquisitionService;
	
	@Autowired
	ProductDao productDao;

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BasketService basketService;
	
	@Autowired
	UserOrderDao userOrderDao;

	Category CATEGORY_1 = new Category("CATEGORY_1");
	Category CATEGORY_2 = new Category("CATEGORY_2");

	Product PRODUCT_1 = new Product(CATEGORY_1, "description1", 100.100, "noIcon.png");
	Product PRODUCT_2 = new Product(CATEGORY_1, "description2", 200.200, "noIcon.png");
	Product PRODUCT_3 = new Product(CATEGORY_2, "description3", 300.300, "noIcon.png");
	
	Acquisition ACQUISITION_1 = new Acquisition( PRODUCT_1, 100L );
	Acquisition ACQUISITION_2 = new Acquisition( PRODUCT_1, 50L );
	Acquisition ACQUISITION_3 = new Acquisition( PRODUCT_1, 30L );
	
	Acquisition ACQUISITION_4 = new Acquisition( PRODUCT_2, 30L );
	
	User USER_1 = new User("user", "pass");
	
	
	@Test
	@Transactional
	public void stockTest()
	{
		UserOrder USER_ORDER_1 = new UserOrder(basketService.getBasket(), Calendar.getInstance(), USER_1, "", "", "", "",PaymentMethod.CREDIT);

		
		categoryDao.save(CATEGORY_1);
		categoryDao.save(CATEGORY_2);
		
		productDao.save(PRODUCT_1);
		productDao.save(PRODUCT_2);
		productDao.save(PRODUCT_3);
		
		acquisitionDao.save(ACQUISITION_1);
		acquisitionDao.save(ACQUISITION_2);
		acquisitionDao.save(ACQUISITION_3);
		acquisitionDao.save(ACQUISITION_4);
		
		Assert.assertEquals(180L, acquisitionService.stockAmount(PRODUCT_1));
		Assert.assertEquals(30L, acquisitionService.stockAmount(PRODUCT_2));
		Assert.assertEquals(0L, acquisitionService.stockAmount(PRODUCT_3));
		
		userDao.save(USER_1);
		basketService.addToBasket(PRODUCT_1, 50L);
		basketService.addToBasket(PRODUCT_1, 20L);
		
		userOrderDao.save(USER_ORDER_1);
		Assert.assertEquals(110L, acquisitionService.stockAmount(PRODUCT_1));
		
		basketService.newBasket();
		
		basketService.addToBasket(PRODUCT_1, 10L);
		basketService.addToBasket(PRODUCT_2, 10L);
		
		UserOrder USER_ORDER_2 = new UserOrder(basketService.getBasket(), Calendar.getInstance(), USER_1, "", "", "", "",PaymentMethod.CREDIT);

		userOrderDao.save(USER_ORDER_2);
		Assert.assertEquals(100L, acquisitionService.stockAmount(PRODUCT_1));
		Assert.assertEquals(20L, acquisitionService.stockAmount(PRODUCT_2));
	}
}
