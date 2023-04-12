package com.alten.shop.products.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Product")
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name="code", length = 50, nullable = false)
    String code;
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name="name", length = 50, nullable = false)
    String name;
    @NotBlank
    @Size(min = 1, max = 500)
    @Column(name="description", length = 500, nullable = false)
    String description;
    @NotNull
    @Column(name="price", length = 10, nullable = false)
    float price;
    @NotNull
    @Column(name="quantity", length = 50, nullable = false)
    int quantity;
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name="inventoryStatus", length = 50, nullable = false)
    String inventoryStatus;
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name="category", length = 50, nullable = false)
    String category;
    @Column(name="image", length = 100)
    String image;
    @Column(name="rating", length = 50)
    int rating;

    public void update(Product productDetail) {
      if(productDetail.image != null){
        this.image = productDetail.image;
      }
      if(productDetail.code != null){
        this.code = productDetail.code;
      }
      if(productDetail.name != null){
        this.name = productDetail.name;
      }
      if(productDetail.description != null){
        this.description = productDetail.description;
      }
      this.price = productDetail.price;
      this.quantity = productDetail.quantity;
      if(productDetail.inventoryStatus != null){
        this.inventoryStatus = productDetail.inventoryStatus;
      }
      if(productDetail.category != null){
        this.category = productDetail.category;
      }
      this.rating = productDetail.rating;
    }
}
