package com.ojas.employee.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.ojas.employee.controller.EmployeeController;
import com.ojas.employee.model.Employee;
import com.ojas.employee.response.EmployeeResponse;
import com.ojas.employee.service.EmployeeServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)


public class EmployeeControllerTest {
	@InjectMocks
	EmployeeController employeeController;

	@Mock
	EmployeeServiceImpl employeeService;

	@Autowired
	private MockMvc mockMvc;
	@Spy
	Employee employee = new Employee();
	@Spy
	EmployeeResponse employeeResponse = new EmployeeResponse();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this.getClass());
	}

	public Employee getEmployee() {
		// Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Rani");
		employee.setPhone((long) 6703644);
		employee.setSal(40000);
		employee.setCity("hyd");
		return employee;
	}

	@Test
	public void testSaveOrUpdateOk() {
		Employee employee = getEmployee();
		employeeResponse.setData(employee);
		employeeResponse.setStatusCode("200");
		employeeResponse.setMessage("Success");

		when(employeeService.saveorupdateEmployee(employee)).thenReturn(employeeResponse);
		System.out.println(employeeController);
		ResponseEntity<Object> savedResponse = employeeController.saveorupdate(employee);
		System.out.println(savedResponse);
		assertEquals(savedResponse.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testSaveOrUpdateConflict() {
		employeeResponse.setData(employee);
		employeeResponse.setStatusCode("200");
		employeeResponse.setMessage("Success");

		when(employeeService.saveorupdateEmployee(null)).thenReturn(employeeResponse);
		System.out.println(employeeController);
		ResponseEntity<Object> savedResponse = employeeController.saveorupdate(employee);
		System.out.println(savedResponse);
		assertEquals(savedResponse.getStatusCode(), HttpStatus.CONFLICT);
	}

	@Test
	public void testGetByIdOk() {
		Employee employee = getEmployee();
		employeeResponse.setData(employee);
		employeeResponse.setStatusCode("200");
		employeeResponse.setMessage("Success");
		when(employeeService.getById(1)).thenReturn(employeeResponse);
		ResponseEntity<Object> actualResponse = employeeController.getById(1);
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	public void testGetByIdConflict() {
		ResponseEntity<Object> actualResponse = employeeController.getById(null);
		assertEquals(HttpStatus.CONFLICT, actualResponse.getStatusCode());
	}

	@Test
	public void testGetallOk() {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee());
		employeeResponse.setData(employee);
		employeeResponse.setStatusCode("200");
		employeeResponse.setMessage("Success");

		when(employeeService.getAllEmployee(0, 10)).thenReturn(employeeResponse);
		ResponseEntity<Object> responseEntity = employeeController.getAll(0, 10);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	public void testGetallConflict() {
		ResponseEntity<Object> responseEntity = employeeController.getAll(null, 10);
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());

	}

	@Test
	public void testDeleteOk() {
		employeeResponse.setData(employee);
		employeeResponse.setStatusCode("200");
		employeeResponse.setMessage("Success");
		when(employeeService.deleteEmployee(1)).thenReturn(employeeResponse);
		ResponseEntity<Object> actualResponse = employeeController.deleteEmployee(1);
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	public void testDeleteConflict() {
		ResponseEntity<Object> response =  employeeController.deleteEmployee(null);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}
}