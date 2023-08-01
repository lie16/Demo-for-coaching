package com.practice.demo.entities;

import java.io.Serializable;

public class EmployeeDto implements Serializable  {
    private int employeeId;
    private String employeeName;
    private String email;
    private String phoneNumber;

    private DepartmentEntDto departmentDto;

    private String departmentName;

    private int totalData;
    private int totalHalaman;
}
