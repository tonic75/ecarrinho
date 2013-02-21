package br.com.neolog.ecarrinho.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.dao.CategoryDao;

/**
 * @author antonio.moreira
 *
 * The final DAO for Category.
 * Passes his type for GenericDaoJpa constructor.
 */
@Component
public class CategoryDaoJpa extends GenericDaoJpa<Category, String> implements CategoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public CategoryDaoJpa() {
		super(Category.class);
	}
}