package com.shopping.productservice.controller;

import com.shopping.productservice.dto.CreatedResponse;
import com.shopping.productservice.dto.ProductRequest;
import com.shopping.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    // @Valid will be triggered before @PreAuthorize, which is misleading, move @Valid to service layer
    public ResponseEntity<Object> createProduct(@RequestBody  ProductRequest productRequest) {

        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CreatedResponse.builder()
                        .message("Product created successfully for name %s".formatted(productRequest.getName()))
                        .build());
    }

    @GetMapping()
    public ResponseEntity<Object> productResponseList() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.productResponseList());
    }

}
