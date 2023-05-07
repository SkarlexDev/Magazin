package com.shop.services;

import java.util.List;

import com.shop.struct.Category;

public interface CategoryService {

    List<Category> getAll();

    void saveCategory(String name);

    void deleteCategory(Long id);

    Category getByName(String name);
}
