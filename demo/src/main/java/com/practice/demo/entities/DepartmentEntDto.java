package com.practice.demo.entities;

import java.io.Serializable;

public class DepartmentEntDto implements Serializable {
    private String departmentName;

    // standard getters and setters
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;

    }
}
