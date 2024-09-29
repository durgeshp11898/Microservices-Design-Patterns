package com.command.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.command.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
