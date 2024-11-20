package com.khushi.ESD.mapper;

import org.springframework.stereotype.Service;

import com.khushi.ESD.dto.CustomerRequest;
import com.khushi.ESD.dto.CustomerResponse;
import com.khushi.ESD.entity.Customer;


@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
    public CustomerResponse toCustomerResponse(Customer customer) {
        return new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress(), customer.getCity(), customer.getPincode());
    }
}
