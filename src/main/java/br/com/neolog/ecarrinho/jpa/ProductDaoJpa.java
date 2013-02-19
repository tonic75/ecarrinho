package br.com.neolog.ecarrinho.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
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

	@PersistenceContext
	EntityManager entityManager;
	
	public ProductDaoJpa() {
		super(Product.class);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategory(Category category) {
		
		Query query = entityManager.createQuery("from Product p where p.category =:category");
		query.setParameter("category", category);
		
		return (List<Product>) query.getResultList();
	}

}
