package com.practice.demo.entities;

import java.io.Serializable;

//TODO apakah dto ini bener, atau DTO nya seharusnya gabung dengan response?
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
