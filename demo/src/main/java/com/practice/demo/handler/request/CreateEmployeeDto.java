package com.practice.demo.handler.request;


import lombok.Data;

@Data
public class CreateEmployeeDto {
  private String employeeName;
  private String email;
  private String phoneNumber;
  private int departmentId;
}
