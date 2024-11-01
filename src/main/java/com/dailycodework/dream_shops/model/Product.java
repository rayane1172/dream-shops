package com.dailycodework.dream_shops.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
// here we don't use @Data because we create a copy for this class in addrequest class
// @AllArgsConstructor -> we don't need to use it  bcz we write one specially 
@NoArgsConstructor
@Entity
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String brand;
   private BigDecimal price;
   private Integer inventory;
   private String description;


   //this relation between this product and category will removed when product removed
   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "category_id")
   private Category category;


   //TODO: when a product was deleted ,all associated images will deleted
   @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Image> images;


   // manullay make own constructor
    public Product(String name, String brand, BigDecimal price, Integer inventory, String description, Category category) {
      this.name = name;
      this.brand = brand;
      this.price = price;
      this.inventory = inventory;
      this.description = description;
      this.category = category;
    }






}
