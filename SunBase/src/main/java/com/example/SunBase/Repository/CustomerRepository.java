package com.example.SunBase.Repository;


import com.example.SunBase.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer , Integer> {

    @Override
    List<Customer> findAll();
    Optional<Customer> findById(Integer customerId);

    @Query("select u from Customer u where (:firstName is null or u.firstName = :firstName)")
    List<Customer> findByFirstName(String firstName);

    @Query("select u from Customer u where (:lastName is null or u.lastName = :lastName)")
    List<Customer> findByLastName(String lastName);

    @Query("select u from Customer u where (:email is null or u.email = :email)")
    List<Customer> findByEmail(String email);

    @Query("select u from Customer u where (:phone is null or u.phone = :phone)")
    List<Customer> findByPhone(String phone);

}
