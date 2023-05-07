package com.shop.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.repository.CategoryRepository;
import com.shop.services.CategoryService;
import com.shop.struct.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        List<Category> category = new ArrayList<>();
        categoryRepository.findAll().forEach(category::add);
        return category;
    }

    @Override
    public void saveCategory(String name) {
        categoryRepository.save(new Category(name));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

}
