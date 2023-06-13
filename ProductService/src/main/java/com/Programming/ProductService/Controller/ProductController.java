package com.Programming.ProductService.Controller;

import com.Programming.ProductService.DTO.ProductRequest;
import com.Programming.ProductService.DTO.ProductResponse;
import com.Programming.ProductService.Service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController
{


    private final ProductService productService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void CreateProduct(@RequestBody ProductRequest productRequest) {
        productService.CreateProduct(productRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> productResponseList() {
        return productService.productResponseList();

    }

}
