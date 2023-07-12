package com.practice.demo.repository;

import com.practice.demo.entities.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
  Optional<Users> findByEmail(String email);
}
