package com.example.SunBase.Controller;


import com.example.SunBase.Entities.Customer;
import com.example.SunBase.Repository.CustomerRepository;
import com.example.SunBase.Services.CustomerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    @Autowired
    CustomerRepository customerRepository;
    @PostMapping("/login")
    public String login(@RequestParam String login_id, @RequestParam String password) {
        // Perform authentication logic here
        if ("XXXXXXXuserid".equals(login_id) && "XXXpassword".equals(password)) {

            return "<script>window.location.href = '/customerList.html';</script>";
        } else {
            return "Login failed";
        }
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String login_id, @RequestParam String password) {
//        // Perform authentication logic here
//        if ("test@sunbasedata.com".equals(login_id) && "Test@123".equals(password)) {
//            String token = jwtUtil.generateToken(login_id);
//            return token;
//        } else {
//            return "Login failed";
//        }
//    }
    @GetMapping("/CustomerList1")
    public List<Customer> getAllCustomers(@RequestParam(required = false) String searchType, @RequestParam(required = false) String q) {
        if (searchType != null && q != null) {
            if (searchType.equals("firstName")) {
                return customerServices.getCustomersByFirstName(q);
            } else if (searchType.equals("lastName")) {
                return customerServices.getCustomersByLastName(q);
            } else if (searchType.equals("email")) {
                return customerServices.getCustomersByEmail(q);
            } else if (searchType.equals("phone")) {
                return customerServices.getCustomersByPhone(q);
            }
        }
        return (List<Customer>) customerServices.getAllCustomers();
//        return customerServices.getAllCustomers();
    }

    @PostMapping("/Registration")
    public String addCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String street,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String email,
            @RequestParam String phone) {


        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .street(street)
                .address(address)
                .city(city)
                .state(state)
                .email(email)
                .phone(phone)
                .build();

        customerServices.addCustomer(customer);

        return "<script>window.location.href = '/customerList.html';</script>";
    }

    @GetMapping("/CustomerList")
    public List<Customer> getAllCustomers() {
        return customerServices.getAllCustomers();
    }


    @GetMapping("/api/customers/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable Integer customerId) {
        // Retrieve customer details from the database based on the provided customer ID
        return customerRepository.findById(customerId);
//                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    }

    @PutMapping("/update-customer/{customerId}")
    public String updateCustomerDetails(@PathVariable Integer customerId,  @RequestParam String firstName,
                                        @RequestParam String lastName,
                                        @RequestParam String street,
                                        @RequestParam String address,
                                        @RequestParam String city,
                                        @RequestParam String state,
                                        @RequestParam String email,
                                        @RequestParam String phone ) {
        // Retrieve the existing customer from the database
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return "Customer not found with ID: " + customerId;
        }
        Customer existingCustomer = optionalCustomer.get();

        // Update the customer details with the new values
        existingCustomer.setFirstName(firstName);
        existingCustomer.setLastName(lastName);
        existingCustomer.setStreet(street);
        existingCustomer.setAddress(address);
        existingCustomer.setCity(city);
        existingCustomer.setState(state);
        existingCustomer.setEmail(email);
        existingCustomer.setPhone(phone);

        // Save the updated customer to the database
        customerRepository.save(existingCustomer);

        return "Customer updated successfully";
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public  String deleteCustomer(@PathVariable Integer customerId)
    {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return "Customer not found with ID: " + customerId;
        }
        Customer customer =optionalCustomer.get();
        customerRepository.delete(customer);
        return "<script>window.location.href = '/customerList.html';</script>";
    }


}


































