package br.com.neolog.ecarrinho.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.neolog.services.WS.WS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/beans.xml")
public class WSTest {
	
	@Autowired
	private WS ws;
	
	@Test
	public void WsTest()
	{
		Assert.assertEquals(true, ws.isBaskSlipOk("papiu"));
	}
}