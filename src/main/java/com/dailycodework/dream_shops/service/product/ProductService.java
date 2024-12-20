package com.dailycodework.dream_shops.service.product;

import java.util.List;
import java.util.Optional;

import com.dailycodework.dream_shops.dto.ImageDto;
import com.dailycodework.dream_shops.dto.ProductDto;
import com.dailycodework.dream_shops.exceptions.AlreadyExistsException;
import com.dailycodework.dream_shops.exceptions.ResourceNotFoundException;
import com.dailycodework.dream_shops.model.Image;
import com.dailycodework.dream_shops.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dailycodework.dream_shops.exceptions.ProductNotFoundException;
import com.dailycodework.dream_shops.model.Category;
import com.dailycodework.dream_shops.model.Product;
import com.dailycodework.dream_shops.repository.CategoryRepository;
import com.dailycodework.dream_shops.repository.ProductRepository;
import com.dailycodework.dream_shops.request.AddProductRequest;
import com.dailycodework.dream_shops.request.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

   //todo-> i should add final to inject this dependancy because we write the anotation @requiredArgsconstructor
   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;
   private final ImageRepository imageRepository;
   private final ModelMapper modelMapper;



   private Product createProduct(AddProductRequest request, Category category) {
      return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getInventory(),
            request.getDescription(),
            category);
   }
   @Override
   public Product addProduct(AddProductRequest request) {
      // chech if the category is found in DB
      // if yes, set it as the new product category
      // if no,then save it as a new category then set it as the new product category

      if (productExists(request.getName(),request.getBrand())){
         throw new AlreadyExistsException(request.getBrand()+" "+request.getName()
                 +" already exists, you may update this product instead...");
      }
      Category category = Optional.ofNullable(categoryRepository
                                    .findByName(request.getCategory().getName()))
                                    .orElseGet(() -> {
               Category newCategory = new Category(request.getCategory().getName());
               return categoryRepository.save(newCategory);
            });
      Product product = new Product(request.getName(),
            request.getBrand(),
               request.getPrice(),
                  request.getInventory(),
                     request.getDescription(), category);
                        request.setCategory(category);
      productRepository.save(createProduct(request, category));
      return product;
   }

   //hipo method
   private boolean productExists(String name, String brand){
      return productRepository.existsByNameAndBrand(name, brand);
   }


   @Override
   public Product updateProduct(ProductUpdateRequest productUpdateRequest, Long productId) {
      return productRepository.findById(productId)
      .map(existingProduct -> updateExistingProduct(existingProduct, productUpdateRequest))
      .map(productRepository :: save )
            .orElseThrow(() ->
                        new ProductNotFoundException("Product Not found!!"));
   }



   private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest productUpdateRequest){
      existingProduct.setName(productUpdateRequest.getName());
      existingProduct.setBrand(productUpdateRequest.getBrand());
      existingProduct.setPrice(productUpdateRequest.getPrice());
      existingProduct.setInventory(productUpdateRequest.getInventory());
      existingProduct.setDescription(productUpdateRequest.getDescription());

      //todo-> update category of exs product from the request update product
      Category category = categoryRepository.findByName(productUpdateRequest.getCategory().getName());
      existingProduct.setCategory(category);
      return existingProduct;
   }


   @Override
   public List<Product> getAllProducts() {
      return productRepository.findAll();
   }

   @Override
   public List<Product> getProductsByCategory(String category) {
      return productRepository.findByCategoryName(category);
   }

   @Override
   public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
      return productRepository.findByCategoryNameAndBrand(category, brand);
   }

   @Override
   public List<Product> getProductsByName(String name) {
      return productRepository.findByName(name);
   }

   @Override
   public List<Product> getProductsByBrandAndName(String category, String name) {
      return productRepository.findByBrandAndName(category, name);
   }

   @Override
   public Long countProductsByBrandAndName(String brand, String name) {
      return productRepository.countByBrandAndName(brand, name);
   }

   @Override
   public List<Product> getProductsByBrand(String brand) {
      return productRepository.findByBrand(brand);
   }

   @Override
   public Product getProductById(Long id) {
      return productRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Product Not found"));
   }

   @Override
   public void deleteProductById(Long id) {
      productRepository.findById(id).ifPresentOrElse(productRepository::delete, () ->
      {
         throw new ProductNotFoundException("Product not found to delete it ");
      });
   }


   @Override
   public List<ProductDto> getConvertedProducts(List<Product> products){
      return products.stream().map(this::convertToDto).toList();
   }

   @Override
   public ProductDto convertToDto(Product product){
      ProductDto productDto = modelMapper.map(product,ProductDto.class);
      List<Image> images = imageRepository.findByProductId(product.getId());

      List<ImageDto> imageDtos = images.stream()
              .map(image -> modelMapper.map(image, ImageDto.class)).toList();

      productDto.setImages(imageDtos);
      return productDto;
   }

   
}
