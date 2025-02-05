package com.wecp.progressive.controller;

import com.wecp.progressive.dto.LoginRequest;
import com.wecp.progressive.dto.LoginResponse;
import com.wecp.progressive.entity.Supplier;
import com.wecp.progressive.jwt.JwtUtil;
import com.wecp.progressive.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class LoginController {
    private final LoginService loginService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(LoginService loginService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.loginService = loginService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Supplier> registerUser(@RequestBody Supplier user) {
        return ResponseEntity.ok(loginService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequest loginRequest) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password", e);
        }
        final UserDetails userDetails = loginService.loadUserByUsername(loginRequest.getUsername());
        Supplier foundUser = loginService.getSupplierByName(loginRequest.getUsername());
        final String token = jwtUtil.generateToken(loginRequest.getUsername());
        String role = foundUser.getRole();
        Integer userId = foundUser.getSupplierId();
        System.out.println("User Roles: " + role);
        return ResponseEntity.ok(new LoginResponse(token, role, userId));
    }
}
