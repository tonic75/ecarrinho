package br.com.neolog.ecarrinho.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.neolog.ecarrinho.util.Persistable;

import com.google.common.base.Objects;

/**
 * @author antonio.moreira
 * 
 * This class describres a category of a product.
 * The only field here is the name, that is also used as PK.
 */
@Entity
@Access(AccessType.FIELD)
public class Category implements Persistable {
	
	@Id
	private String name;

	/**
	 * Empty private default contructor for hibernate.
	 */
	@SuppressWarnings("unused")
	private Category(){}
	
	/**
	 * Instantiates a new category.
	 *
	 * @param name the name of the category
	 */
	public Category(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		final Category category = (Category)obj;
		return Objects.equal(this.name, category.name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("Name", name).toString();
	}
}
