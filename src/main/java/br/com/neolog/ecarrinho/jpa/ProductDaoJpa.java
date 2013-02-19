package br.com.neolog.ecarrinho.jpa;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.ProductDao;

/**
 * @author antonio.moreira
 *
 * The final dao for Product.
 * Passes his type for GenericDaoJpa constructor.
 */
@Component
public class ProductDaoJpa extends GenericDaoJpa<Product, Long> implements ProductDao {

	public ProductDaoJpa() {
		super(Product.class);
	}

}
