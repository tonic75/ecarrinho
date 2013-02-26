package br.com.neolog.ecarrinho.jpa;

import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Acquisition;
import br.com.neolog.ecarrinho.dao.AcquisitionDao;

/**
 * The Class AquisitionDaoJpa.
 * 
 * The final DAO for Acquisition.
 * Passes his type for GenericDaoJpa constructor.
 * 
 * @author antonio.moreira
 * */
@Component
public class AcquisitionDaoJpa extends GenericDaoJpa<Acquisition, Long> implements AcquisitionDao {
	public AcquisitionDaoJpa() {
		super(Acquisition.class);
	}
}
