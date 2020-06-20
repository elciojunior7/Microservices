package br.acme.estoque.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.acme.estoque.config.RabbitConfigure;
import br.acme.estoque.model.Product;
import br.acme.estoque.model.Sale;
import br.acme.estoque.repository.ProductRepository;

@Service
public class StockService {

	Logger logger = LoggerFactory.getLogger(StockService.class);

	Environment environment;
	ProductRepository productRepository;

	// As melhores práticas pedem p/ não injetar usando @Autowired e sim colocar no
	// construtor
	public StockService(ProductRepository productRepository, Environment environment) {
		this.productRepository = productRepository;
		this.environment = environment;
	}

	public Long save(Product product) {
		return productRepository.save(product).getId();
	}

	public List<Product> getProducts() {
		logger.info("StockService.getProducts= " + environment.getProperty("local.server.port"));
		return productRepository.findAll();
	}

	public Product getProductById(Long idProduct) {
		return productRepository.findById(idProduct).orElseThrow(() -> new RuntimeException("Produto não localizado"));
	}

	@RabbitListener(queues = RabbitConfigure.SALE_QUEUE)
	public void consumer(Sale sale) {
		Product product = getProductById(sale.getIdProduct());
		product.setStock(product.getStock() - sale.getAmount());
		save(product);
	}

	public void updateStock(Sale sale) {
		Product product = getProductById(sale.getIdProduct());
		product.setStock(product.getStock() - sale.getAmount());
		save(product);
	}

}
