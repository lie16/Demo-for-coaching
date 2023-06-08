package com.practice.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int employeeId;
  private String employeeName;
  private String email;
  private String phoneNumber;

  @OneToOne(fetch = FetchType.LAZY)
  private Department department;

}
