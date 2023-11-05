package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.dto.StoreDTO;
import com.ibssoln.shoppers.entity.Category;
import com.ibssoln.shoppers.service.CategoryService;
import com.ibssoln.shoppers.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories() throws ShoppersException {
        List<Category> categories = new ArrayList<>();
        try {
            categories = categoryService.getAllCategories();
        }catch (Exception e){
            throw new ShoppersException("Error occurred while fetching categories.");
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
