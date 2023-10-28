package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.entity.Vendor;
import com.ibssoln.shoppers.repo.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor findOrCreateVendor(String id, String name, String address){
        return vendorRepository.findById(id).orElse(vendorRepository.save(Vendor.builder().id(id).name(name).address(address).build()));
    }

    public Vendor findVendor(String id){
        return vendorRepository.findById(id).orElseThrow(() ->  new RuntimeException("The vendor does not exist."));
    }

    public Vendor updateVendor(String id, String name, String address){
        Vendor vendor = findVendor(id);
        vendor.setName(name);
        vendor.setAddress(address);
        return vendorRepository.save(vendor);
    }

    public Iterable<Vendor> getAll(){
        return vendorRepository.findAll();
    }

    public long getCountAllVendors(){
        return vendorRepository.count();
    }

}
