package com.practice.demo.repository;

import com.practice.demo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

  public Department findDepartmentByDepartmentName(String departmentName);

}
