package com.khushi.ESD.service;

import com.khushi.ESD.dto.CustomerRequest;
import com.khushi.ESD.dto.LoginRequest;
import com.khushi.ESD.entity.Customer;
import com.khushi.ESD.helper.JwtUtil;
import com.khushi.ESD.mapper.CustomerMapper;
import com.khushi.ESD.repo.CustomerRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.password()));
        customerRepo.save(customer);
        return "Customer created successfully";
    }

    public String login(LoginRequest request) {
        Optional<Customer> customerOptional = customerRepo.findByEmail(request.email());

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (passwordEncoder.matches(request.password(), customer.getPassword())) {//raw string, encrypt. pass
                return jwtUtil.generateToken(customer.getEmail());
            }
        }
        throw new RuntimeException("Invalid credentials");
    }
}
