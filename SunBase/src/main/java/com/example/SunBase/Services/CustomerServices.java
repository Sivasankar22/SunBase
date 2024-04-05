package com.example.SunBase.Services;


import com.example.SunBase.Entities.Customer;
import com.example.SunBase.Repository.CustomerRepository;
import com.example.SunBase.RequestDtos.AddCustomerRequest;
import com.example.SunBase.Transformers.CustomerTransforms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServices {

    @Autowired
    CustomerRepository customerRepository;


    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "Customer has been added to the Database successfully";
    }
    public String editCustomer(Integer customerId, String firstName, String lastName, String street, String address, String city, String state, String email, String phone) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            // Update the customer details
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setStreet(street);
            customer.setAddress(address);
            customer.setCity(city);
            customer.setState(state);
            customer.setEmail(email);
            customer.setPhone(phone);
            // Save the updated customer
            customerRepository.save(customer);
            return "Customer with ID " + customerId + " has been successfully edited";
        } else {
            return "Customer with ID " + customerId + " not found";
        }
    }



    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }
    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }
    public List<Customer> getCustomersByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public List<Customer> getCustomersByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}













































