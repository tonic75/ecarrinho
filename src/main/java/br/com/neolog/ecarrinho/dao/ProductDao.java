package br.com.neolog.ecarrinho.dao;

import java.util.List;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;

/**
 * The Interface ProductDao.
 * 
 * @author antonio.moreira
 * 
 *         A interface for a generic DAO for Product.
 */
public interface ProductDao extends GenericDao<Product, Long> {

	/**
	 * Gets all the products in a category.
	 * 
	 * @param category
	 *            the category
	 * @return the products by category
	 */
	public List<Product> getProductsByCategory(Category category);

	/**
	 * Gets all the products that contains in their description the String
	 * passed as parameter.
	 * 
	 * @param description
	 *            the description
	 * @return the products by description
	 */
	public List<Product> getProductsByDescription(String description);
}
