package br.com.neolog.ecarrinho.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Acquisition;
import br.com.neolog.ecarrinho.bean.Category;
import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.dao.CategoryDao;
import br.com.neolog.ecarrinho.dao.ProductDao;
import br.com.neolog.ecarrinho.service.AcquisitionService;

/**
 * Add initial products and category to the system.
 * 
 * @author antonio.moreira
 */
@Component
public class AddInitialData {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	AcquisitionService acquisitionService;
	
	public void productPersistence()
	{
		Category Automotivo = new Category("Automotivo");
		categoryDao.save(Automotivo);
		Product GPS_5_POL = new Product(Automotivo, "GPS 5 Polegadas", 259.00, "gps5pol.jpg");
		productDao.save(GPS_5_POL);
		productDao.save(new Product(Automotivo, "CD Player Sony", 329.00, "cdsony.jpg"));
		productDao.save(new Product(Automotivo, "DVD Ícone", 499.00, "dvdicone.jpg"));
		
		
		Category CamaMesaBanho = new Category("Cama, Mesa e Banho");
		categoryDao.save(CamaMesaBanho);
		productDao.save(new Product(CamaMesaBanho, "Jogo 4 Toalhas Felpudas", 69.90, "toalhasfelpudas.jpg"));
		productDao.save(new Product(CamaMesaBanho, "Kit Coruja Solteiro", 99.90, "corujasolteiro.jpg"));
		productDao.save(new Product(CamaMesaBanho, "Travesseiro Toque de Pessego", 19.90, "toquedepessego.jpg"));
		productDao.save(new Product(CamaMesaBanho, "Toalha de Mesa Redonda", 59.90, "toalhamesaredonda.jpg"));

		
		Category Smartphone = new Category("Smartphone");
		categoryDao.save(Smartphone);
		productDao.save(new Product(Smartphone, "Motorola RAZR i", 1299.00, "razri.jpg"));
		productDao.save(new Product(Smartphone, "Motorola Me XT303", 349.00, "xt303.jpg"));
		productDao.save(new Product(Smartphone, "Samsung Galaxy Y", 399.00, "galaxyy.jpg"));
		productDao.save(new Product(Smartphone, "Apple iPhone 4", 1799.00, "iphone4.jpg"));
		productDao.save(new Product(Smartphone, "Nokia Lumia 710", 549.00, "lumia710.jpg"));
		productDao.save(new Product(Smartphone, "Nokia Lumia 800", 899.00, "lumia800.jpg"));
		productDao.save(new Product(Smartphone, "Sony Xperia P", 999.00, "xperiap.jpg"));

		Category Papelaria = new Category("Papelaria");
		categoryDao.save(Papelaria);
		productDao.save(new Product(Papelaria, "Calculadora Financeira Casio", 199.00, "calccasio.jpg"));
		productDao.save(new Product(Papelaria, "Mochila Galinha Pintadinha Popopo", 119.00, "mochilagalinha.jpg"));
		productDao.save(new Product(Papelaria, "Caneta Roller Conquist ", 199.00, "caneta.jpg"));

		Category Flores = new Category("Flores");
		categoryDao.save(Flores);
		productDao.save(new Product(Flores, "Coração Chic de Rosas Vermelhas", 49.60, "coracaochic.jpg"));
		productDao.save(new Product(Flores, "Primavera das Rosas", 144.60, "primavera.jpg"));
		productDao.save(new Product(Flores, "Sempre Juntos", 45.80, "semprejuntos.jpg"));
		productDao.save(new Product(Flores, "Hora de Comemorar", 116.80, "comemorar.jpg"));	
		
		acquisitionService.addAcquisition(new Acquisition(GPS_5_POL, 10L));
		acquisitionService.addAcquisition(new Acquisition(GPS_5_POL, 5L));
	}
}