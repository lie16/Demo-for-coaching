package com.practice.demo.services;

import com.practice.demo.entities.Department;
import com.practice.demo.handler.request.CreateBulkDepartmentDto;
import com.practice.demo.handler.request.CreateDepartmentDto;
import com.practice.demo.repository.DepartmentRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

  @Autowired
  private DepartmentRepo departmentRepo;



  public String createDepartment(CreateDepartmentDto createDepartmentDto) throws Exception {
    Department department = new Department();
    if (departmentRepo.findDepartmentByDepartmentName(createDepartmentDto.getDepartmentName()) == null) {
      department.setDepartmentName(createDepartmentDto.getDepartmentName());
      department.setCreatedDate(new Date());
      department.setUpdatedDate(new Date());
    }else  {
      throw new Exception("Department name already exists, you should give the unique name of the department");
    }
    departmentRepo.save(department);
    return "Department created successfully";
  }


  @Transactional
  public String createDepartmentBulk(CreateBulkDepartmentDto createBulkDepartmentDto) {

    List<Department> departments = new ArrayList<>();

    for (CreateDepartmentDto createDepartmentDto : createBulkDepartmentDto.getDepartmentNames()) {
          Department department = new Department();
          department.setDepartmentName(createDepartmentDto.getDepartmentName());
          department.setCreatedDate(new Date());
          department.setUpdatedDate(new Date());
          departments.add(department);
    }

    departmentRepo.saveAll(departments);
    return "Department successfully saved";

  }


}
