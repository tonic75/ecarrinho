package br.com.neolog.ecarrinho.bean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
@Access(AccessType.FIELD)
public class Basket {

	@Id
	@GeneratedValue
	private Long id;
	
	@ElementCollection
	private Map<Product, Long> products = new HashMap<Product, Long>();
	
	public Map<Product, Long> getBasket()
	{
		return Collections.unmodifiableMap(products);
	}
	
	public void addToBasket( Product product, Long amount )
	{
		if( product != null && amount > 0)
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
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public void changeAmount(Product product,Long newAmount)
	{
		if( product != null && newAmount > 0 )
		{
			products.put(product, newAmount);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public void remove(Product product)
	{
		if( product != null )
		{
			products.remove(product);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public double getTotalValue()
	{
		double totalValue = 0;
		for (Product product : products.keySet()) {
			totalValue += product.getPrice()*products.get(product);
		}
		return totalValue;
	}
	
	public Map<Product, Long> getProducts()
	{
		return Collections.unmodifiableMap(products);
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
