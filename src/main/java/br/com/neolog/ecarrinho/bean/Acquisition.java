package br.com.neolog.ecarrinho.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.neolog.ecarrinho.util.Persistable;

import com.google.common.base.Objects;

/**
 * The Class Acquisition. This class represents an acquisition of certain amount
 * of certain product for company's stock.
 * 
 * @author antonio.moreira
 */
@Entity
public class Acquisition implements Persistable
{

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Product product;

	@Basic
	private Long amount;

	@SuppressWarnings( "unused" )
	private Acquisition()
	{
	}

	public Acquisition( Product product, Long amount )
	{
		this.product = product;
		this.amount = amount;
	}

	public Product getProduct()
	{
		return product;
	}

	public Long getAmount()
	{
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode( id );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		final Acquisition acquisition = (Acquisition) obj;
		return Objects.equal( this.id, acquisition.id );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Objects.toStringHelper( this ).add( "Product: ", product ).add( "Amount: ", amount ).toString();
	}
}
