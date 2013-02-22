package br.com.neolog.ecarrinho.jpa;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.UserOrder;
import br.com.neolog.ecarrinho.dao.UserOrderDao;

/**
 * The Class UserOrderDao.
 * 
 * The final DAO for UserOrder.
 * Passes his type for GenericDaoJpa constructor.
 * 
 * @author antonio.moreira
 * */
@Component
public class UserOrderDaoJpa extends GenericDaoJpa<UserOrder, Long> implements UserOrderDao {

	public UserOrderDaoJpa() {
		super(UserOrder.class);
	}

}
