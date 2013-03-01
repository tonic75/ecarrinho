package br.com.neolog.ecarrinho.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.neolog.ecarrinho.util.Persistable;

import com.google.common.base.Objects;

/**
 * @author antonio.moreira
 * 
 *         The Class Product. Class that describes a product with his category,
 *         description, price and its icon.
 */
@Entity
@Access( AccessType.FIELD )
public class Product implements Persistable
{

	public static final String ICON_PATH = "src//main//resources//icon//";

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Category category;

	@Basic
	private String description;

	@Basic
	private Double price;

	@Basic
	private String iconName;

	/**
	 * Empty default constructor for hibernate.
	 */
	public Product()
	{
	}

	public Product( Category category, String description, Double price, String iconName )
	{
		this.category = category;
		this.description = description;
		this.price = price;
		this.iconName = iconName;
	}

	public Long getId()
	{
		return id;
	}

	public String getDescription()
	{
		return description;
	}

	public Double getPrice()
	{
		return price;
	}

	public String getIconName()
	{
		return iconName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hashCode( description, price );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		final Product product = (Product) obj;
		return Objects.equal( this.price, product.price ) && Objects.equal( this.description, product.description );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Objects.toStringHelper( this ).add( "Description", description ).add( "Price", price ).toString();
	}
}
