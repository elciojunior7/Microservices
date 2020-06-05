package br.acme.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.acme.estoque.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}