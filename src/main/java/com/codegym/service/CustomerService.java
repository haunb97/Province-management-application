package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.model.Province;

import java.util.List;

public interface CustomerService {
    Iterable<Customer> findAll();
    Iterable<Customer> findAllByProvince(Province province);

    Customer findById(Long id);

    void save(Customer customer);

    void remove(Long id);
}
