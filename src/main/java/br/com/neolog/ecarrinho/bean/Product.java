package br.com.neolog.ecarrinho.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class Product.
 * Class that describes a product
 */
@Entity
public class Product {

	/** The id. */
	@Id
	@GeneratedValue
	private int id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The price. */
	private Double price;
	
	/**
	 * Instantiates a new product.
	 */
	public Product() {}
	
	/**
	 * Instantiates a new product.
	 *
	 * @param description the description
	 * @param price the price
	 */
	public Product( String name, String description, Double price )
	{
		this.name = name;
		this.description = description;
		this.price = price;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		if(description.length() == 0)
			throw new IllegalArgumentException("Empty description");
		else
			this.description = description;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Double price) {
		if(price < 0.0)
			throw new IllegalArgumentException("Negative price");
		else
			this.price = price;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}
