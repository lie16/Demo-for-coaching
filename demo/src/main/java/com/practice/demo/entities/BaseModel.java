package com.practice.demo.entities;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel {
  private Date createdDate;
  private Date updatedDate;
}
