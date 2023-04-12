package com.alten.shop.products.controller;

import com.alten.shop.products.entity.Product;
import com.alten.shop.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) throws Exception{
        Map<String, Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            throw new Exception("Les donnees ne sont pas valides");
        }
        productService.createProduct(product);
        result.put("product", product);

        return ResponseEntity.ok(result);

    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, Object>> getAllProducts() throws Exception{
        Map<String, Object> result = new HashMap<>();
        List<Product> products = productService.getAllProducts();
        result.put("products", products);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable("id") int productId) throws Exception{
        Map<String, Object> result = new HashMap<>();
        Product product = productService.getProduct(productId);
        result.put("product", product);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Object>> removeProduct(@PathVariable("id") int productId) throws Exception{
        Map<String, Object> result = new HashMap<>();
        productService.deleteProduct(productId);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Map<String, Object>> createProduct(@PathVariable("id") int productId, @Valid @RequestBody Product product, BindingResult bindingResult) throws Exception{
        Map<String, Object> result = new HashMap<>();
        if(bindingResult.hasErrors()){
            throw new Exception("Les donnees ne sont pas valides");
        }
        productService.updateProduct(productId, product);
        result.put("product", product);

        return ResponseEntity.ok(result);

    }
}
