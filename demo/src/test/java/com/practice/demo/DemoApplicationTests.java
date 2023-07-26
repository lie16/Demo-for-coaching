package com.practice.demo;

import com.practice.demo.entities.Department;
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


	@InjectMocks
	private EmployeeServices employeeServices;


	@Test
	void contextLoads() {
	}

}
