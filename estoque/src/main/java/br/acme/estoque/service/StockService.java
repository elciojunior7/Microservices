package br.acme.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.acme.estoque.model.Product;
import br.acme.estoque.model.Sale;
import br.acme.estoque.repository.ProductRepository;

@Service
public class StockService {

	@Autowired
	ProductRepository productRepository;

	public Long save(Product product) {
		return productRepository.save(product).getId();
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public Product getProduct(Long idProduct) {
		return productRepository.findById(idProduct).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}

	public void updateStock(Sale sale) {
		Product product = getProduct(sale.getIdProduct());
		product.setStock(product.getStock() - sale.getAmount());
		save(product);
	}

}
