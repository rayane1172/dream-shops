package com.dailycodework.dream_shops.controller;

import com.dailycodework.dream_shops.exceptions.ResourceNotFoundException;
import com.dailycodework.dream_shops.model.Product;
import com.dailycodework.dream_shops.request.AddProductRequest;
import com.dailycodework.dream_shops.request.ProductUpdateRequest;
import com.dailycodework.dream_shops.response.ApiResponse;
import com.dailycodework.dream_shops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {


    private final IProductService productService; // final = dependancy injection with the anotations above (requredargs..)

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return  ResponseEntity.ok(new ApiResponse("Success..",products));
    }


    @GetMapping("product/{productId}/product") // make sur that the path variable is named as in endpoint URL
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            return  ResponseEntity.ok(new ApiResponse("Success..",product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("add product Success..",theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct (@RequestBody ProductUpdateRequest request, @PathVariable Long productId){
        try {
            Product theProduct = productService.updateProduct(request,productId);
            return ResponseEntity.ok(new ApiResponse("update product success",theProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct (@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product Deleted successfully..",null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

//todo -> 2:33:47


    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName){
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found ",null));
            }
            return ResponseEntity.ok(new ApiResponse("Success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category,brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found !!",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }



    @GetMapping("/{name}")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name){
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found !!",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }



    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand){
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found !!",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category){
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products for found (category !!",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> findProductByCategory(@RequestParam String category,@RequestParam String name){
        try {
            var productCount = productService.countProductsByBrandAndName(category,name);
            return ResponseEntity.ok(new ApiResponse("Product count !!",null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
        }
    }























}
