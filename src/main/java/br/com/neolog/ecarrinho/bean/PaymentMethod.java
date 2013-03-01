package br.com.neolog.ecarrinho.bean;

/**
 * The Enum PaymentMethod. Enum to represent the possible methods of payment.
 * 
 * @author antonio.moreira
 */
public enum PaymentMethod
{

	/** Credit card. */
	CREDIT( "Crédito" ),

	/** Bank slip. */
	BANK_SLIP( "Boleto" ),

	/** Debit directly in the account. */
	DEBIT( "Débito" );

	/** A String to represent a name to be showed to the user. */
	String beautifulName;

	/**
	 * Instantiates a new payment method with its name.
	 * 
	 * @param beautifulName
	 *            the beautiful name
	 */
	private PaymentMethod( String beautifulName )
	{
		this.beautifulName = beautifulName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return beautifulName;
	}
}
