package br.com.neolog.ecarrinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Acquisition;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.AcquisitionDao;

/**
 * The service for the entity Acquisition.
 * 
 * @author antonio.moreira
 */
@Component
public class AcquisitionService
{

	@Autowired
	private AcquisitionDao acquisitionDao;

	@Autowired
	private UserOrderService userOrderService;

	public void addAcquisition( Acquisition acquisition )
	{
		acquisitionDao.save( acquisition );
	}

	/**
	 * This calculates how much of a certain product is available in stock. Its
	 * done by the sum of the acquisitions of that product minus the sum of the
	 * orders of this product.
	 * 
	 * @param product
	 *            that is wanted to know the amount is stock
	 * @return the amount available to be sold of that product
	 */
	public long stockAmount( Product product )
	{
		return totalAcquistionsByProduct( product ) - userOrderService.getTotalAmountProductSold( product );
	}

	private long totalAcquistionsByProduct( Product product )
	{
		List<Acquisition> acquisitions = acquisitionDao.getAll();
		long total = 0;
		for( Acquisition acquisition : acquisitions )
		{
			if( acquisition.getProduct().equals( product ) ) total += acquisition.getAmount();
		}
		return total;
	}
}
