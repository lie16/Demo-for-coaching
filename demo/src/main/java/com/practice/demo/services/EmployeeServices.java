package com.practice.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.entities.Department;
import com.practice.demo.entities.Employee;
import com.practice.demo.entities.EmployeeDto;
import com.practice.demo.handler.request.CreateEmployeeDto;
import com.practice.demo.handler.response.DepartmentDto;
import com.practice.demo.handler.response.EmployeesResponse;
import com.practice.demo.repository.EmployeeRepo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServices {

  @Autowired
  private EmployeeRepo employeeRepo;

  @Transactional
  public String createEmployee(CreateEmployeeDto createEmployeeDto) {
    Employee employee = new Employee();
    employee.setEmployeeName(createEmployeeDto.getEmployeeName());
    employee.setEmail(createEmployeeDto.getEmail());
    employee.setPhoneNumber(createEmployeeDto.getPhoneNumber());
    employee.setCreatedDate(new Date());
    employee.setUpdatedDate(new Date());

//    ambil data department dari department service --------------------------------
    String url = "http://localhost:8882/api-v1/department/detail?departmentId=" + createEmployeeDto.getDepartmentId();
    RestTemplate restTemplate = new RestTemplate();
    Department department = restTemplate.getForObject(url, Department.class);
//    ---------------------------------------------------------------

    employee.setDepartment(department);
    employeeRepo.save(employee);
    return "employee created successfully";
  }

  public List<EmployeesResponse> getEmployees() {
//    pagination
    Pageable pageable = PageRequest.of(0, 10);
    Page<Employee> page = employeeRepo.findAll(pageable);

    List<EmployeesResponse> responses = new ArrayList<>();
    for (Employee employee : page) {
      EmployeesResponse employeesResponse = new EmployeesResponse();
      employeesResponse.setEmployeeId(employee.getEmployeeId());
      employeesResponse.setEmail(employee.getEmail());
      employeesResponse.setPhoneNumber(employee.getPhoneNumber());
      employeesResponse.setEmployeeName(employee.getEmployeeName());

//      get department data
      String url = "http://localhost:8882/api-v1/department/detail?departmentId=" + employee.getDepartment();
      RestTemplate restTemplate = new RestTemplate();
      Department department = restTemplate.getForObject(url, Department.class);


//      Department department = employee.getDepartment(); //call api dept service

      DepartmentDto departmentDto = new DepartmentDto();
      departmentDto.setDepartmentName(department.getDepartmentName());
      employeesResponse.setDepartmentName(department.getDepartmentName());

      employeesResponse.setDepartmentDto(departmentDto);

      employeesResponse.setTotalData(page.getTotalElements());

      employeesResponse.setTotalHalaman(page.getTotalPages());

      responses.add(employeesResponse);
    }
    return responses;
  }

  public List<EmployeesResponse> getEmployeFromOtherServices(){
    String url = "http://localhost:8883/api-v1/employee";
    RestTemplate restTemplate = new RestTemplate();
//    TODO:: disini bisa gak mapping langsung ke object, jadi pake request for object
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//    System.out.println("body result" + response.getBody());
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = null;
    try {
      root = mapper.readTree(response.getBody());
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
//    System.out.println("root result" + root.toString());
    List<EmployeesResponse> responses = new ArrayList<>();
    if (root.isArray()) {

      for (final JsonNode objNode : root) {
//        System.out.println(objNode);
//        ndek sini baru terjadi mapping terhadap dto untuk ta lempar
        EmployeesResponse employeesResponse = new EmployeesResponse();
        employeesResponse.setEmployeeId(objNode.path("employeeId").asInt());
        employeesResponse.setEmail(objNode.path("email").asText());
        employeesResponse.setPhoneNumber(objNode.path("phoneNumber").asText());
        employeesResponse.setEmployeeName(objNode.path("employeeName").asText());

        DepartmentDto departmentDto = new DepartmentDto();
//        TODO: ada cara lebih sederhana untuk multi path ini
//         lalu gimana kalau path dalamnya array di loop dulu?
        departmentDto.setDepartmentName(objNode.path("departmentDto").path("departmentName").asText());
        employeesResponse.setDepartmentDto(departmentDto);

        employeesResponse.setDepartmentName(objNode.path("departmentName").asText());
        employeesResponse.setTotalData(objNode.path("totalData").asInt());
        employeesResponse.setTotalHalaman(objNode.path("totalHalaman").asInt());
        responses.add(employeesResponse);
      }
    }
    return responses;
  }

  @Transactional
  public String updateEmployees(CreateEmployeeDto createEmployeeDto, int employeeId)
     throws Exception {
    Optional<Employee> employee = employeeRepo.findById(employeeId);

    if (!employee.isPresent()) {
      throw new Exception("Employee " + employeeId + " not found");
    }
    //    utk delete (HTTP METHOD DeleteMapping)
    //    employeeRepo.delete(employee.get());
    //    employee.get().setIsdeleted(true);
    employee.get().setEmployeeName(createEmployeeDto.getEmployeeName());
    employee.get().setEmail(createEmployeeDto.getEmail());

    employeeRepo.save(employee.get());

    return "Employees updated";
  }
}
