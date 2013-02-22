package br.com.neolog.ecarrinho.bean;

public enum PaymentMethod {	
	CREDIT("Crédito"), 
	BANK_SLIP("Boleto"), 
	DEBIT("Débito");
	
	String beautifulName;
	
	PaymentMethod( String beautifulName )
	{
		this.beautifulName = beautifulName;
	}
	
	@Override
	public String toString()
	{
		return beautifulName;
	}
}
