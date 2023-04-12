package com.alten.shop.products.service;

import com.alten.shop.products.entity.Product;
import com.alten.shop.products.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    //TODO personalize errors
    public Product updateProduct(int productId, Product productDetail) throws Exception {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));
        product.update(productDetail);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(int productId){
        productRepository.deleteById(productId);
    }
    public Product getProduct(int productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new ExpressionException("Product not found"));
    }
}
