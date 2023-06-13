package com.Programming.ProductService.Service;

import com.Programming.ProductService.DTO.ProductRequest;
import com.Programming.ProductService.DTO.ProductResponse;
import com.Programming.ProductService.Model.Product;
import com.Programming.ProductService.Repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // will auto-inject
@Slf4j
public class ProductService {

    private final ProductRepo productRepo;

    public void CreateProduct(ProductRequest productRequest) {
        Product product= Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        productRepo.save(product);
        // using SLf4J
        log.info("product {} is saved", product.getId());
    }
    public List <ProductResponse> productResponseList() {
        List<Product> products = productRepo.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }
    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }
}
