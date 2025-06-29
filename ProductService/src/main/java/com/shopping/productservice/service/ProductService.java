package com.shopping.productservice.service;

import com.shopping.productservice.dto.ProductRequest;
import com.shopping.productservice.dto.ProductResponse;
import com.shopping.productservice.mapper.ProductMapper;
import com.shopping.productservice.model.Product;
import com.shopping.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor // will auto-inject
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void createProduct( ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();
        productRepository.save(product);

    }

    @Cacheable(value = "productCache")
    public List<ProductResponse> productResponseList() {

        Set<String> nonDuplicateNames = new HashSet<>();
        List<Product> products = productRepository.findAll();
        log.info("products: {}", products.size());
        return products.stream().filter(product -> nonDuplicateNames.add(product.getName())).
                 map(productMapper::mapToProductResponse)
                .toList();
    }

}
