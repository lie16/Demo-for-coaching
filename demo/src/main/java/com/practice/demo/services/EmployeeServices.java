package com.practice.demo.services;

import com.practice.demo.entities.Department;
import com.practice.demo.entities.Employee;
import com.practice.demo.handler.request.CreateEmployeeDto;
import com.practice.demo.handler.response.DepartmentDto;
import com.practice.demo.handler.response.EmployeesResponse;
import com.practice.demo.repository.DepartmentRepo;
import com.practice.demo.repository.EmployeeRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServices {

  @Autowired
  private EmployeeRepo employeeRepo;

  @Autowired
  private DepartmentRepo departmentRepo;

  @Transactional
  public String createEmployee(CreateEmployeeDto createEmployeeDto) {
    Employee employee = new Employee();
    employee.setEmployeeName(createEmployeeDto.getEmployeeName());
    employee.setEmail(createEmployeeDto.getEmail());
    employee.setPhoneNumber(createEmployeeDto.getPhoneNumber());
    employee.setCreatedDate(new Date());
    employee.setUpdatedDate(new Date());

    Optional<Department> department = departmentRepo.findById(createEmployeeDto.getDepartmentId());

    employee.setDepartment(department.get());
    employeeRepo.save(employee);

    employeeRepo.delete(employee);
    return "employee created successfully";
  }

  public List<EmployeesResponse> getEmployees() {
    List<Employee> employees = employeeRepo.findAll();

//    pagination
    Pageable pageable = PageRequest.of(0, 10);
    Page<Employee> page = employeeRepo.findAll(pageable);

    List<EmployeesResponse> responses = new ArrayList<>();
    for (Employee employee : page) {
      EmployeesResponse employeesResponse = new EmployeesResponse();
      employeesResponse.setEmployeeId(employee.getEmployeeId());
      employeesResponse.setEmail(employee.getEmail());
      employeesResponse.setPhoneNumber(employee.getPhoneNumber());
      employeesResponse.setEmployeeName(employee.getEmployeeName());

//      get department data
      Department department = employee.getDepartment();

      DepartmentDto departmentDto = new DepartmentDto();
      departmentDto.setDepartmentName(department.getDepartmentName());

      employeesResponse.setDepartmentDto(departmentDto);

      employeesResponse.setTotalData(page.getTotalElements());

      employeesResponse.setTotalHalaman(page.getTotalPages());

      responses.add(employeesResponse);
    }
    return responses;
  }

  @Transactional
  public String updateEmployees(CreateEmployeeDto createEmployeeDto, int employeeId)
     throws Exception {
    Optional<Employee> employee = employeeRepo.findById(employeeId);

    if (!employee.isPresent()) {
      throw new Exception("Employee " + employeeId + " not found");
    }
    //    utk delete (HTTP METHOD DeleteMapping)
    //    employeeRepo.delete(employee.get());
    //    employee.get().setIsdeleted(true);
    employee.get().setEmployeeName(createEmployeeDto.getEmployeeName());
    employee.get().setEmail(createEmployeeDto.getEmail());

    employeeRepo.save(employee.get());

    return "Employees updated";
  }
}
