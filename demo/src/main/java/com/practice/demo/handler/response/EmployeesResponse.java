package com.practice.demo.handler.response;

import lombok.Data;

//These method support @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
//Full set table
@Data
public class EmployeesResponse {
  private int employeeId;
  private String employeeName;
  private String email;
  private String phoneNumber;

  private DepartmentDto departmentDto;
  private String departmentName;

  private long totalData;

  private int totalHalaman;

}
