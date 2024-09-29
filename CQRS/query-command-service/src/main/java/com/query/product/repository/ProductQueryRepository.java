package com.query.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.query.product.entity.Product;

public interface ProductQueryRepository extends JpaRepository<Product,Long> {

}
