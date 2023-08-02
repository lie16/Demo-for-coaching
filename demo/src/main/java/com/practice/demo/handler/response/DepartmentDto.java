package com.practice.demo.handler.response;

import lombok.Data;

//DTO berlokasi di request dan response
//DTO sama dengan req or resp tapi tidak complete
@Data
public class DepartmentDto {

  private String departmentName;
}
