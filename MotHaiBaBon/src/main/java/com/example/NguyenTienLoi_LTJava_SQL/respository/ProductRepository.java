package com.example.NguyenTienLoi_LTJava_SQL.respository;

import com.example.NguyenTienLoi_LTJava_SQL.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
