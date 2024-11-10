package com.dailycodework.dream_shops.model;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String fileName;
   private String fileType;

//   @JsonIgnore
   @Lob
   private Blob image;

   private String downloadUrl;

   @ManyToOne // TODO: one product has many images
   @JoinColumn(name = "product_id")
//   @JsonIgnore
   private Product product;
   


}
