package com.practice.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Department extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int departmentId;
  private String departmentName;

}
