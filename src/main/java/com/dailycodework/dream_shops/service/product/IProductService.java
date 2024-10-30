package com.dailycodework.dream_shops.service.product;

import java.util.*;

import com.dailycodework.dream_shops.model.Product;
import com.dailycodework.dream_shops.request.AddProductRequest;

import java.lang.*;

import com.dailycodework.dream_shops.request.ProductUpdateRequest;

public interface IProductService {
   
   Product addProduct(AddProductRequest product);

   List<Product> getAllProducts();

   List<Product> getProductsByCategory(String category);

   List<Product> getProductsByCategoryAndBrand(String category, String brand);

   List<Product> getProductsByName(String name);

   List<Product> getProductsByBrandAndName(String category, String name);

   Long countProductsByBrandAndName(String category, String name);
   
   List<Product> getProductsByBrand(String brand);
   Product getProductById(Long id);

   void deleteProductById(Long id);
   Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productId);


}
