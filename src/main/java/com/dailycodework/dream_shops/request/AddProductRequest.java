

package com.dailycodework.dream_shops.request;

import java.math.BigDecimal;

import com.dailycodework.dream_shops.model.Category;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//todo -> we create this class because in best practise  we don't work directly with product entity directly , so we create a copy for the entity
@Data // here you can use @Data directly
public class AddProductRequest {

   private Long id;  
   private String name;
   private String brand;
   private BigDecimal price;
   private Integer inventory;
   private String description;
   private Category category;


}
