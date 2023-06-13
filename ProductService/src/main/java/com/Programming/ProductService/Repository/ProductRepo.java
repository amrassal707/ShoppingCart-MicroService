package com.Programming.ProductService.Repository;

import com.Programming.ProductService.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
}
