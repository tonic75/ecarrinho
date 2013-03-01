package br.com.neolog.ecarrinho.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CategoryService
{

	@Autowired
	private CategoryDao categoryDao;

	private Map<String, Category> categoriesListCache;

	/**
	 * Gets the category.
	 * 
	 * @param categoryName
	 *            the category name
	 * @return the category
	 */
	public Category get( String categoryName )
	{
		return getCache().get( categoryName );
	}

	/**
	 * Gets the all categories.
	 * 
	 * @return the all categories
	 */
	public List<Category> getAll()
	{
		return new ArrayList<Category>( getCache().values() );
	}

	private synchronized Map<String, Category> getCache()
	{
		if( categoriesListCache == null )
		{
			categoriesListCache = new HashMap<String, Category>();
			final List<Category> categories = categoryDao.getAll();
			for( final Category category : categories )
			{
				categoriesListCache.put( category.getName(), category );
			}
		}

		return categoriesListCache;
	}
}
