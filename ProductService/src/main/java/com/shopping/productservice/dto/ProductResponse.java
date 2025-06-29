package com.shopping.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;


    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public void setName(String name) {
        this.name = name;
    }



    public void setPrice() {
        setPrice(null);
    }
    public void setName() {
        setName(null);
    }
    public void setDescription() {
        setDescription(null);
    }
}
