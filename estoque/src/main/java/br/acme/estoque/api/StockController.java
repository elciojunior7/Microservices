package br.acme.estoque.api;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.acme.estoque.model.Product;
import br.acme.estoque.model.Sale;
import br.acme.estoque.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService services;

	@PostMapping("/save")
	public ResponseEntity<Long> addProduct(@RequestBody Product product) {
		return new ResponseEntity<>(services.save(product), OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<>(services.getProducts(), OK);
	}

	@GetMapping("/{idProduct}")
	public ResponseEntity<Product> getProduct(@PathVariable("idProduct") Long idProduct) {
		return new ResponseEntity<>(services.getProduct(idProduct), OK);
	}

	@PostMapping("/updateStock")
	public ResponseEntity<Long> addHistory(@RequestBody Sale sale) {
		services.updateStock(sale);
		return ResponseEntity.ok().build();
	}
}
