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
 * The Class ProductDaoJpa.
 *
 * @author antonio.moreira
 * 
 * The final DAO for Product.
 * Passes his type for GenericDaoJpa constructor.
 */
@Component
public class ProductDaoJpa extends GenericDaoJpa<Product, Long> implements ProductDao {

	@PersistenceContext
	private EntityManager entityManager;

	public ProductDaoJpa() {
		super(Product.class);
	}

	
	/* (non-Javadoc)
	 * @see br.com.neolog.ecarrinho.dao.ProductDao#getProductsByCategory(br.com.neolog.ecarrinho.bean.Category)
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategory(Category category) {
		Query query = entityManager.createQuery("from Product p where p.category =:category");
		query.setParameter("category", category);
		
		return (List<Product>) query.getResultList();
	}


	/* (non-Javadoc)
	 * @see br.com.neolog.ecarrinho.dao.ProductDao#getProductsByDescription(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByDescription(String description) {
		Query query = entityManager.createQuery("from Product p where p.description like:description");
		query.setParameter("description", "%"+ description + "%");
		
		return (List<Product>) query.getResultList();
	}

}
