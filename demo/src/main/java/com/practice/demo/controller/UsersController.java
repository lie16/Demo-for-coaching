package com.practice.demo.controller;

import com.practice.demo.entities.Users;
import com.practice.demo.handler.JwtUtils;
import com.practice.demo.handler.request.SignInRequest;
import com.practice.demo.repository.UsersRepository;
import com.practice.demo.services.UsersService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UsersController {

  @Autowired
  private UsersService usersService;

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtils jwtUtils;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
    if (!usersRepository.findByEmail(signInRequest.getEmail()).isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Email not exist, please register your account!");
    }

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateToken(authentication);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(jwt);
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody Users users){
    try {

      Users response = usersService.registerUser(users);
      return ResponseEntity.ok(response);

    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}
