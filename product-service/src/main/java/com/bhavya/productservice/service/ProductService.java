package com.bhavya.productservice.service;

import com.bhavya.productservice.dto.ProductRequest;
import com.bhavya.productservice.dto.ProductResponse;
import com.bhavya.productservice.model.Product;
import com.bhavya.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

  @Autowired private ProductRepository productRepository;

  public void createProduct(ProductRequest productRequest) {
    Product product =
        Product.builder()
            .description(productRequest.getDescription())
            .name(productRequest.getName())
            .price(productRequest.getPrice())
            .build();

    productRepository.save(product);
    log.info("PRODUCT {} SAVED SUCCESSFULLY.", product.getId());
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(this::mapToProductResponse).toList();
  }

  private ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .description(product.getDescription())
        .name(product.getName())
        .price(product.getPrice())
        .build();
  }
}
