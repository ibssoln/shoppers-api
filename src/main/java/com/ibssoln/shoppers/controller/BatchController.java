package com.ibssoln.shoppers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @RequestMapping(value = "/items", method = RequestMethod.GET) //produces = {"application/json"}, consumes = {"application/json"},
    public ResponseEntity<List<String>> test(){
        return new ResponseEntity(List.of("abc", "def"), HttpStatus.OK);
    }

}
