package br.com.neolog.ecarrinho.dao;

import java.util.List;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;

/**
 * @author antonio.moreira
 *
 *
 * A interface for a generic dao for Product.
 */
public interface ProductDao extends GenericDao<Product, Long> {

	public List<Product> getProductsByCategory( Category category );
}
