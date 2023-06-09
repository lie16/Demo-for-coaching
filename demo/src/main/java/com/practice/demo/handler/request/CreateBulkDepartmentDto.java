package com.practice.demo.handler.request;

import java.util.List;
import lombok.Data;

@Data
public class CreateBulkDepartmentDto {

  private List<CreateDepartmentDto> departmentNames;
}
