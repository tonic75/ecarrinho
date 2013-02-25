package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.ProductDao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * The service for the entity Product.
 * 
 * @author antonio.moreira
 */
@Component
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryService categoryDao;
	
	private Multimap<Category, Product> productsByCategoryCache;
	
	private List<Product> productsListCache;
	
	/**
	 * Gets the all products.
	 *
	 * @return the all products
	 */
	public List<Product> getAllProducts()
	{
		if( productsListCache == null )
		{
			productsListCache = productDao.getAll();
		}
		return productsListCache;
	}
	
	public List<Product> getProductsByCategory(String categoryName)
	{
		if( productsByCategoryCache == null )
		{
			productsByCategoryCache = getAllProductsByCategory();
		}
		
		return (List<Product>) productsByCategoryCache.get(categoryDao.getCategory(categoryName));
	}

	/**
	 * Gets the all products by category.
	 *
	 * @return a map with category as key and the respectives products as values.
	 */
	public Multimap<Category, Product> getAllProductsByCategory()
	{
		
		if( productsByCategoryCache == null )
		{
			productsByCategoryCache = ArrayListMultimap.create();
			
			List<Category> categories = categoryDao.getAll();
			for (Category category : categories) {
				List<Product> products = productDao.getProductsByCategory(category);
				for (Product product : products) {
					productsByCategoryCache.put(category, product);
				}
			}
		}		
		
		return productsByCategoryCache;
	}
	
	/**
	 * Gets the products by description.
	 *
	 * @param description the description
	 * @return a list with all the products found having the passed description included into theirs.
	 */
	public List<Product> getProductsByDescription( String description )
	{
		return productDao.getProductsByDescription(description);
	}
}
