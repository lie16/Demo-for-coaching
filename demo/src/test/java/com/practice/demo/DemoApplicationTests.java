package com.practice.demo;

import com.practice.demo.entities.Department;
import com.practice.demo.repository.DepartmentRepo;
import com.practice.demo.services.EmployeeServices;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	@Mock
	private DepartmentRepo departmentRepo;

	@InjectMocks
	private EmployeeServices employeeServices;


	@Test
	void contextLoads() {
	}

	@Test
	public void createDepartmentTest() throws Exception {
		Department department = new Department();
		Assertions.assertNull(departmentRepo.findDepartmentByDepartmentName("Bussiness Development"));
		department.setDepartmentName("Bussiness Development");
		department.setCreatedDate(new Date());
		department.setUpdatedDate(new Date());
		departmentRepo.save(department);
		System.out.println("Department created successfully");
	}

//	@Test
//	public void createDepartmentServiceTest() {
//		CreateEmployeeDto createEmployeeDto = new CreateEmployeeDto();
//
//		createEmployeeDto.setEmployeeName("Anton");
//		createEmployeeDto.setDepartmentId(1);
//		createEmployeeDto.setEmail("anton@gmail.com");
//		createEmployeeDto.setPhoneNumber("123");
//		Assertions.assertEquals("employee created successfully",employeeServices.createEmployee(createEmployeeDto));
//	}
}
