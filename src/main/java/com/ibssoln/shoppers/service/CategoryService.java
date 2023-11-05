package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.dao.CategoryDaoImpl;
import com.ibssoln.shoppers.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    public List<Category> getAllCategories(){
        return (List<Category>) categoryDaoImpl.getAll();
    }

}
