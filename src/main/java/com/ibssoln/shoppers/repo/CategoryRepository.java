package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {



}
