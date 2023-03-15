package com.exceltodb.service;

import com.exceltodb.entity.Customer;
import com.exceltodb.helper.CustomerHelper;
import com.exceltodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void save(MultipartFile file)
    {
        try{
            List<Customer>customerList= CustomerHelper.convertExcelToListOfCustomer(file.getInputStream());
            customerRepository.saveAll(customerList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public List<Customer>getAllCustomer()
    {
        return customerRepository.findAll();
    }
}
