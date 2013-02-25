package br.com.neolog.ecarrinho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.UserOrder;
import br.com.neolog.ecarrinho.dao.UserOrderDao;

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
	public void save( UserOrder userOrder )
	{
		userOrderDao.save(userOrder);
	}
}
