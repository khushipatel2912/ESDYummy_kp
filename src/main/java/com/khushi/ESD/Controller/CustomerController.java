package com.khushi.ESD.Controller;

import com.khushi.ESD.dto.CustomerRequest;
import com.khushi.ESD.dto.LoginRequest;
import com.khushi.ESD.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String token = customerService.login(request);
        return ResponseEntity.ok(token);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@RequestBody Long id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }
}
