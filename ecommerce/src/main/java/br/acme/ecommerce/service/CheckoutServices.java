package br.acme.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.acme.ecommerce.model.Sale;
import br.acme.ecommerce.repository.SalesRepository;

@Service
public class CheckoutServices {

	// private static final String URL = "http://localhost:8090/stock/updateStock";

	@Autowired
	SalesRepository salesRepository;

	// @Autowired
	// public RestTemplate restTemplate;

	public void saleProduct(final Sale sale) {
		salesRepository.save(sale);
		// sendHistoryToStock(sale);
	}

//	private void sendHistoryToStock(final Sale sale) {
//		restTemplate.postForObject(URL, sale, Sale.class);
//	}

}
