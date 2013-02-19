package br.com.neolog.ecarrinho.jpa;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.dao.CategoryDao;

/**
 * @author antonio.moreira
 *
 * The final dao for Category.
 * Passes his type for GenericDaoJpa constructor.
 */
@Component
public class CategoryDaoJpa extends GenericDaoJpa<Category, String> implements CategoryDao {

	public CategoryDaoJpa() {
		super(Category.class);
	}

}
