package br.com.neolog.ecarrinho.forms;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.service.BasketService;
import br.com.neolog.ecarrinho.service.ProductService;
import br.com.neolog.ecarrinho.util.WrapLayout;

/**
 * The Class ProductsPanel. This is a JScrollPane with a panel that holds
 * products on it. It can show all the products persisted or only a list of
 * them.
 * 
 * @author antonio.moreira
 */
@Component
public class ProductsPanel extends JPanel {

	@Autowired
	private ProductService productService;

	@Autowired
	private BasketService basketService;

	// this is here to update do basket screen when a user add a product there
	// with the screen opened
	@Autowired
	private ProductsOnBasketHolder productsOnBasketHolder;

	private static final long serialVersionUID = 1L;

	public ProductsPanel() {
		this.setLayout(new WrapLayout(FlowLayout.LEFT, 0, 0));
	}

	/**
	 * Load all products persisted.
	 */
	public void loadProducts() {
		addProducts(productService.getAllProducts());
	}

	/**
	 * Load a list of products.
	 * 
	 * @param products
	 *            the products to be showed
	 */
	public void loadProducts(List<Product> products) {
		addProducts(products);
	}

	private void addProducts(List<Product> products) {
		removeAll();
		for (Product product : products) {
			add(new ProductSpot(product, this));
		}
	}

	public void addToBasket(Product product, Long amount) {
		basketService.addToBasket(product, amount);
		productsOnBasketHolder.refreshBasket();
	}
}