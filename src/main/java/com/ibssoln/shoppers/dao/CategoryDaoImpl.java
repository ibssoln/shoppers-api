package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Category;
import com.ibssoln.shoppers.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryDaoImpl {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getAll(){
        return categoryRepository.findAll();
    }

}
