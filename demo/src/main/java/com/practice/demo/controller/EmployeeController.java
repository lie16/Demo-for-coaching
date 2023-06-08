package com.practice.demo.controller;


import com.practice.demo.entities.Employee;
import com.practice.demo.handler.request.CreateEmployeeDto;
import com.practice.demo.handler.response.CreateEmployeeResponse;
import com.practice.demo.handler.response.EmployeesResponse;
import com.practice.demo.repository.EmployeeRepo;
import com.practice.demo.services.EmployeeServices;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api-v1/employee")
public class EmployeeController {

  @Autowired
  private EmployeeServices employeeService;

  @PostMapping("create")
  public ResponseEntity createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
      String result = employeeService.createEmployee(createEmployeeDto);
      CreateEmployeeResponse response = new CreateEmployeeResponse();
      response.setStatus("Created");
      response.setStatusCode(HttpStatus.CREATED.value());
      response.setResultMessage(result);
      return ResponseEntity.status(response.getStatusCode()).body(response);
  }

  @GetMapping
  public ResponseEntity getEmployees() {
    List<EmployeesResponse> employees = employeeService.getEmployees();
    return ResponseEntity.ok(employees);
  }

  @PutMapping("update")
  public ResponseEntity updateEmployee(@RequestBody CreateEmployeeDto createEmployeeDto, @RequestParam("employeeid") int employeeId) {
    try {
      String response = employeeService.updateEmployees(createEmployeeDto, employeeId);
      return ResponseEntity.ok(response);
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
