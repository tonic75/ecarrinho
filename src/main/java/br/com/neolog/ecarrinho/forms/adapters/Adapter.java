package br.com.neolog.ecarrinho.forms.adapters;

import java.awt.Window;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.forms.MainFrame;
import br.com.neolog.ecarrinho.service.UserOrderService;
import br.com.neolog.ecarrinho.util.MustBeLogged;
import br.com.neolog.ecarrinho.util.NotEmptyBasket;

@Component
public class Adapter
{

	@Autowired
	private UserOrderService userOrderService;
	@Autowired
	private MainFrame mainFrame;

	@MustBeLogged
	public void callPayment( Window window )
	{
		mainFrame.setVisible( false );
		window.setVisible( true );
	}

	@NotEmptyBasket
	public void callBasket( Window window )
	{
		window.setVisible( true );
	}
}
