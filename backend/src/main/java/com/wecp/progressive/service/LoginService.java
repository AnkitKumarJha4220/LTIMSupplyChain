package com.wecp.progressive.service;

import com.wecp.progressive.entity.Supplier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public class LoginService implements UserDetailsService {

    public List<Supplier> getAllUsers() {
        return null;
    }

    public Optional<Supplier> getUserById(Integer userId) {
        return null;
    }

    public Supplier getSupplierByName(String username) {
        return null;
    }

    public Supplier createUser(Supplier user) {
        return null;
    }

    public Supplier updateUser(Supplier user) {
        return null;
    }

    public void deleteUser(Integer id) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}