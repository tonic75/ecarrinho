package br.com.neolog.ecarrinho.forms;

import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.ecarrinho.bean.Product;
import br.com.neolog.ecarrinho.service.AcquisitionService;
import br.com.neolog.ecarrinho.service.BasketService;
import br.com.neolog.ecarrinho.util.WrapLayout;

/**
 * This panel holds the other panels that represents a product in the basket,
 * ProductOnBasketPanel. 
 * 
 * @author antonio.moreira
 */
@Component
public class ProductsOnBasketHolder extends JPanel {
	private static final long serialVersionUID = 3390301978309979543L;
	@Autowired
	private BasketService basketService;

	@Autowired
	private AcquisitionService acquisitionService;

	private JTextField totalValue;

	public ProductsOnBasketHolder() {
		this.setLayout(new WrapLayout(FlowLayout.LEFT, 0, 0));
	}

	public void refreshBasket() {
		removeAll();
		Map<Product, Long> productsOnBasket = basketService.getProducts();
		refreshProducts(productsOnBasket);
		refreshTotalValue(productsOnBasket);
		validate();
		repaint();
	}

	private void refreshProducts(Map<Product, Long> productsOnBasket) {

		for (Product product : productsOnBasket.keySet()) {
			add(new ProductOnBasketPanel(product,
					productsOnBasket.get(product), this));
		}
	}

	private void refreshTotalValue(Map<Product, Long> productsOnBasket) {
		totalValue.setText(String.valueOf(basketService.getTotalValue()));
	}

	public void changeAmount(Product product, Long newAmount) {
		try {
			basketService.changeAmount(product, newAmount);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(
					getParent(),
					"Quantidade pedida não disponível em estoque.\n"
							+ "Quantidade disponível: "
							+ acquisitionService.stockAmount(product), "!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void remove(Product product) {
		basketService.remove(product);
	}

	public void setTotalValue(JTextField totalValue) {
		this.totalValue = totalValue;
	}
}