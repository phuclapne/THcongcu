package com.example.NguyenTienLoi_LTJava_SQL.service;

import com.example.NguyenTienLoi_LTJava_SQL.entity.Product;
import com.example.NguyenTienLoi_LTJava_SQL.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> GetAll() {
        return repo.findAll();
    }
    public Product getProductById(int id) {
        Optional<Product> optional = repo.findById(id);
        return optional.orElse(null);
    }
    public void addProduct(Product book) {
        repo.save(book);
    }
    public void updateProduct(Product book) {
        repo.save(book);
    }

    public void remove (int id)
    {
        repo.deleteById(id);
    }
}
