package br.acme.estoque.api;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

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
@RequestMapping("/estoque")
public class StockController {

	private StockService estoqueService;

	// As melhores práticas pedem p/ não injetar usando @Autowired na variável e sim
	// colocar no construtor
	public StockController(StockService estoqueService) {
		this.estoqueService = estoqueService;
	}

	@PostMapping("/save")
	public ResponseEntity<Long> addProduct(@RequestBody Product product) {
		return new ResponseEntity<>(estoqueService.save(product), OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<>(estoqueService.getProducts(), OK);
	}

	@GetMapping("/{idProduct}")
	public ResponseEntity<Product> getProduct(@PathVariable("idProduct") Long idProduct) {
		return new ResponseEntity<>(estoqueService.getProductById(idProduct), OK);
	}

	@PostMapping("/updateSale")
	public ResponseEntity<Long> updateSale(@RequestBody Sale sale) {
		estoqueService.updateStock(sale);
		return ResponseEntity.ok().build();
	}
}
