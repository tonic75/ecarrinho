package br.com.neolog.ecarrinho.dao;

import br.com.neolog.ecarrinho.bean.User;

public interface UserDao extends GenericDao<User> {
	public User getUserbyUserName( String user );
}
