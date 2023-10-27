package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Vendor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, String> {



}
