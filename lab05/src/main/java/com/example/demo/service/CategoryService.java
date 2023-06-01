package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.reponsitory.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id){
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }else{
            throw new RuntimeException("Category not found");
        }
    }

    public CategoryEntity saveCategory(CategoryEntity category){
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
