package br.com.neolog.ecarrinho.util;

import java.awt.Window;

import org.springframework.stereotype.Component;

@Component
public class JFrameAspectAdapter {

	public void windowCaller( Window w )
	{
		w.setVisible(true);
	}
}
