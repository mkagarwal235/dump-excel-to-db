package com.exceltodb.controller;

import com.exceltodb.entity.Customer;
import com.exceltodb.helper.CustomerHelper;
import com.exceltodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/upload")
    public ResponseEntity<?>upload(@RequestParam("file") MultipartFile file)
    {
        if(CustomerHelper.checkFormat(file))
        {
            customerService.save(file);
            return ResponseEntity.ok(Map.of("message","File is uploaded and saved in db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please upload excelSheet only");
    }

    @GetMapping("/customer")
    public List<Customer>getCustomer()
    {
        return customerService.getAllCustomer();
    }

}
