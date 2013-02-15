package br.com.neolog.ecarrinho.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.neolog.ecarrinho.util.Persistable;

@Entity
public class Category implements Persistable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Basic
	private String name;
}
