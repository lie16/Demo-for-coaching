package com.practice.demo.services;

import com.practice.demo.entities.Users;
import com.practice.demo.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return usersRepository.findByEmail(email)
          .orElseThrow(() -> new UsernameNotFoundException("email not found"));
  }


  public Users registerUser(Users user) throws Exception {
    boolean userExists = usersRepository.findByEmail(user.getEmail()).isPresent();

    if (!userExists) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersRepository.save(user);
        return user;
    } else {
      throw new Exception("email already exist");
    }
  }

}
