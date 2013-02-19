package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.dao.CategoryDao;

@Component
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	public List<Category> getAllCategories()
	{
		return categoryDao.getAll();
	}

}
