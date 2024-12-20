package com.dailycodework.dream_shops.service.category;

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.List;
import java.util.Optional;

import com.dailycodework.dream_shops.exceptions.AlreadyExistsException;
import org.springframework.stereotype.Service;

import com.dailycodework.dream_shops.exceptions.ResourceNotFoundException;
import com.dailycodework.dream_shops.model.Category;
import com.dailycodework.dream_shops.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor // constructor injection
public class CategoryService implements ICategoryService {


   private final CategoryRepository categoryRepository;



    @Override
   public Category getCategoryById(Long id) {
      return categoryRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Category not found!!"));
   }

   @Override
   public Category getCategoryByName(String name) {
       return null;
   }

   @Override
   public List<Category> getAllCategories() {
      return categoryRepository.findAll();
   }


   @Override
   public Category addCategory(Category category) {
      return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName())) // if not available
              .map(categoryRepository :: save)
              .orElseThrow(
                      () -> new AlreadyExistsException(category.getName() + " already exists")
              );
   }

   @Override
   public Category updateCategory(Category category, Long id) {
      return Optional.ofNullable(getCategoryById(id))
      .map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
      })
            .orElseThrow(() -> new ResourceNotFoundException("Category Not Found !!"));
   }

   @Override
   public void deleteCategoryById(Long id) {
      categoryRepository.findById(id)
      .ifPresentOrElse(categoryRepository::delete,
                        () -> {
                        throw new ResourceNotFoundException("Category not found !! ");
                        }
      );
   }
   










}
