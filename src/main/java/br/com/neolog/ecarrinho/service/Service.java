package br.com.neolog.ecarrinho.service;

public interface Service
{
	public boolean isCreditOk( String cardNumber );

	public boolean isDebtOk( String agency, String account );
}
