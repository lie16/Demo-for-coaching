package com.practice.demo.handler.response;


import lombok.Data;

@Data
public class CreateEmployeeResponse {
  private String status;
  private int statusCode;
  private String resultMessage;
}
