package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.bean.UserOrder;
import br.com.neolog.ecarrinho.dao.UserOrderDao;
import br.com.neolog.ecarrinho.util.MustBeLogged;

/**
 * The service for the class UserOrder.
 * 
 * @author antonio.moreira
 */
@Component
public class UserOrderService {

	@Autowired
	private UserOrderDao userOrderDao;

	/**
	 * Save a UserOrder.
	 *
	 * @param userOrder the user order
	 */
	@MustBeLogged
	public void save( UserOrder userOrder )
	{
		userOrderDao.save(userOrder);
	}

	@Transactional
	public long getTotalAmountProductSold(Product product) {
		List<UserOrder> userOrders =  userOrderDao.getAll();
		long total = 0;
		for (UserOrder userOrder : userOrders) {
			if( userOrder.getBasket().getBasket().containsKey(product) )
				total += userOrder.getBasket().getBasket().get(product);
		}
		return total;
	}
}
