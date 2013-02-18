package br.com.neolog.ecarrinho.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.ImmutableList;

import br.com.neolog.ecarrinho.bean.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/beans.xml" })
public class UserDaoTest {
	
	@Autowired
	UserDao dao;
	
	public static final User USER_01 = new User("user1", "senha");
	public static final User USER_02 = new User("user2", "senha");
	public static final User USER_03 = new User("user3", "senha");
	public static final User USER_04 = new User("user4", "senha");
	public static final User USER_05 = new User("user5", "senha");
	public static final User USER_06 = new User("user6", "senha");
	
	List<User> users = new ImmutableList.Builder<User>()
			.add(USER_01)
			.add(USER_02)
			.add(USER_03)
			.add(USER_04)
			.add(USER_05)
			.add(USER_06)
			.build();			
	
	@Test
	public void UserPersistence()
	{
		dao.save(USER_01);
		dao.save(USER_02);
		dao.save(USER_03);
		dao.save(USER_04);
		dao.save(USER_05);
		dao.save(USER_06);
		
		List<User> fromDB = dao.getAll();
		
		Assert.assertEquals(6, fromDB.size());
		Assert.assertEquals(users.toString(), fromDB.toString());
		
		dao.delete(USER_06);
		fromDB = dao.getAll();
		Assert.assertEquals(5, fromDB.size());
		
		Assert.assertEquals(dao.get(USER_03.getUser()).toString(), USER_03.toString());
	}

}
