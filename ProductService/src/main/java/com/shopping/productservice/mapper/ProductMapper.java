package com.shopping.productservice.mapper;

import com.shopping.productservice.dto.ProductResponse;
import com.shopping.productservice.model.Product;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {



    ProductResponse mapToProductResponse(Product product);
}
