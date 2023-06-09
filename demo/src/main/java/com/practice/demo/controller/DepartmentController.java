package com.practice.demo.controller;

import com.practice.demo.handler.request.CreateBulkDepartmentDto;
import com.practice.demo.handler.request.CreateDepartmentDto;
import com.practice.demo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api-v1/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;


  @PostMapping("create")
  public ResponseEntity createDepartment(@RequestBody CreateDepartmentDto createDepartmentDto) {
    try {
      String response = departmentService.createDepartment(createDepartmentDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("create-bulk")
  public ResponseEntity createDepartmentBulk(@RequestBody
                                                 CreateBulkDepartmentDto createBulkDepartmentDto) {
    try {
      String response = departmentService.createDepartmentBulk(createBulkDepartmentDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
