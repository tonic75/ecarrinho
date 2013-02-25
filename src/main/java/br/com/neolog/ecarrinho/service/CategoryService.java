package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.dao.CategoryDao;

/**
 * The service for the entity Category.
 * 
 * @author antonio.moreira
 */
@Component
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	private List<Category> categoriesListCache;
	
	/**
	 * Gets the category.
	 *
	 * @param categoryName the category name
	 * @return the category
	 */
	public Category getCategory( String categoryName )
	{
		if( categoriesListCache == null )
		{
			categoriesListCache = categoryDao.getAll();
		}
		for (Category category : categoriesListCache) {
			if( category.getName().equals(categoryName)  )
				return category;
		}
		return null;
	}
	
	/**
	 * Gets the all categories.
	 *
	 * @return the all categories
	 */
	public List<Category> getAllCategories()
	{
		if( categoriesListCache == null )
		{
			categoriesListCache = categoryDao.getAll();
		}
		return categoriesListCache;
	}

	public List<Category> getAll() {
		if( categoriesListCache == null )
		{
			categoriesListCache = getAllCategories();
		}
		return categoriesListCache;
	}

}
