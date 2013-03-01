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

import br.com.neolog.ecarrinho.util.Persistable;

import com.google.common.base.Objects;

/**
 * The Class Basket. This class represents a collection of products that the
 * user of the system selects for buying.
 * 
 * @author antonio.moreira
 */
@Entity
@Access( AccessType.FIELD )
public class Basket implements Persistable
{

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * The products. This map contains what products are going to be bought and
	 * their respective amount;.
	 */
	@ElementCollection
	private Map<Product, Long> products = new HashMap<Product, Long>();

	/**
	 * Returns a immutable version of the basket.
	 * 
	 * @return the basket
	 */
	public Map<Product, Long> getBasket()
	{
		return Collections.unmodifiableMap( products );
	}

	/**
	 * Adds the to basket.
	 * 
	 * @param product
	 *            the product
	 * @param amount
	 *            the amount of the product. If the product is already in the
	 *            basket, its previous amount is add to this new
	 */
	public void addToBasket( Product product, Long amount )
	{
		if( product != null && amount > 0 )
		{
			if( !products.containsKey( product ) )
			{
				products.put( product, amount );
			}
			else
			{
				products.put( product, products.get( product ) + amount );
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Change amount.
	 * 
	 * @param product
	 *            the product
	 * @param newAmount
	 *            the new amount
	 */
	public void changeAmount( Product product, Long newAmount )
	{
		if( product != null && newAmount > 0 )
		{
			products.put( product, newAmount );
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Removes the product of the basket.
	 * 
	 * @param product
	 *            the product
	 */
	public void remove( Product product )
	{
		if( product != null )
		{
			products.remove( product );
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets the total value of the products in the basket.
	 * 
	 * @return the total value
	 */
	public double getTotalValue()
	{
		double totalValue = 0;
		for( Product product : products.keySet() )
		{
			totalValue += product.getPrice() * products.get( product );
		}
		return totalValue;
	}

	public Long getId()
	{
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode( products );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		final Basket basket = (Basket) obj;
		return Objects.equal( this.products, basket.products );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Objects.toStringHelper( this ).add( "Number of products", products.size() ).toString();
	}
}
