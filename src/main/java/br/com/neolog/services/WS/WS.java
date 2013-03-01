package br.com.neolog.services.WS;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WS {
	@WebMethod
	public boolean isBaskSlipOk( String name );
}
