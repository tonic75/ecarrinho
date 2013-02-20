package br.com.neolog.ecarrinho.jpa;

import br.com.neolog.ecarrinho.bean.Acquisition;
import br.com.neolog.ecarrinho.dao.AcquisitionDao;

// TODO: Auto-generated Javadoc
/**
 * The Class AquisitionDaoJpa.
 * 
 * The final DAO for Acquisition.
 * Passes his type for GenericDaoJpa constructor.
 * 
 * @author antonio.moreira
 * */
public class AcquisitionDaoJpa extends GenericDaoJpa<Acquisition, Long> implements AcquisitionDao {
	public AcquisitionDaoJpa() {
		super(Acquisition.class);
	}
}
