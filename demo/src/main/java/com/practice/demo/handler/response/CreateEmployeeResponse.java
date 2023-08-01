package com.practice.demo.handler.response;


import lombok.Data;

//These method support @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
@Data
public class CreateEmployeeResponse {
  private String status;
  private int statusCode;
  private String resultMessage;
}
