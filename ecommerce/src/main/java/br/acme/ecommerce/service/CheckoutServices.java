package br.acme.ecommerce.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.acme.ecommerce.config.RabbitConfigure;
import br.acme.ecommerce.model.Sale;
import br.acme.ecommerce.repository.SalesRepository;

@Service
public class CheckoutServices {

	private static final String URL = "http://localhost:8090/estoque/updateSale";

	@Autowired
	SalesRepository salesRepository;

	@Autowired
	public RestTemplate restTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void saleProduct(final Sale sale) {
		salesRepository.save(sale);
		sendHistoryToStockByRabbt(sale);
	}

	/*
	 * Atualiza estoque via fila de maneira assincrona
	 */
	private void sendHistoryToStockByRabbt(final Sale sale) {
		rabbitTemplate.convertAndSend(RabbitConfigure.SALE_EX, "", sale);
	}

	private void updateStock(final Sale sale) {
		restTemplate.postForObject(URL, sale, Sale.class);
	}

}
