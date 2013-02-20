package br.com.neolog.ecarrinho.bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.base.Objects;

@Component
public class Basket {

	private Map<Product, Long> products = new HashMap<Product, Long>();
	
	public void addToBasket( Product product, Long amount )
	{
		if( !products.containsKey(product) )
		{
			products.put(product, amount);
		}
		else
		{
			products.put(product, products.get(product) + amount);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(products);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		final Basket basket = (Basket)obj;
		return Objects.equal(this.products, basket.products);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("Number of products", products.size()).toString();
	}
}
