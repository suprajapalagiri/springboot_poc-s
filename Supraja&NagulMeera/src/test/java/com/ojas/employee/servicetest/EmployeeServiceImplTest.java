package com.ojas.employee.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import com.ojas.employee.controller.EmployeeController;
import com.ojas.employee.model.Employee;
import com.ojas.employee.repository.EmployeeRepository;
import com.ojas.employee.response.EmployeeResponse;
import com.ojas.employee.service.EmployeeServiceImpl;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class)


public class EmployeeServiceImplTest {
	@InjectMocks
	EmployeeServiceImpl employeeService;

	@Mock
	EmployeeController employeeController;

	@Mock
	EmployeeRepository employeeRepository;

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
		when(employeeRepository.save(employee)).thenReturn(employee);
		EmployeeResponse saveorupdateEmployee = employeeService.saveorupdateEmployee(employee);
		assertEquals(saveorupdateEmployee.getStatusCode(), "200");
	}

	@Test
	public void testSaveOrUpdatenotOk() {
		Employee employee = null;
		when(employeeRepository.save(null)).thenReturn(employee);
		EmployeeResponse saveorupdateEmployee = employeeService.saveorupdateEmployee(null);
		assertEquals(saveorupdateEmployee.getStatusCode(), "422");
	}

	@Test
	public void getById() {
		Optional<Employee> employee = Optional.of(getEmployee());
		when(employeeRepository.findById(1)).thenReturn(employee);
		EmployeeResponse getById = employeeService.getById(1);
		assertEquals(getById.getStatusCode(), "200");
	}

	@Test
	public void getByIdNotOk() {
		when(employeeRepository.findById(1)).thenReturn(null);
		EmployeeResponse getById = employeeService.getById(null);
		assertEquals(getById.getStatusCode(), "422");
	}

	@Test
	public void testFindAll() {
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(getEmployee());
		Page<Employee> pageEmp = new PageImpl<>(listEmp);
		when(employeeRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pageEmp);
		EmployeeResponse response = employeeService.getAllEmployee(0, 5);
		assertEquals("200", response.getStatusCode());
	}

	@Test
	public void testFindAllNotOK() {

		Page mock = Mockito.mock(Page.class);
		when(employeeRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(mock);
		EmployeeResponse response = employeeService.getAllEmployee(0, 5);
		assertEquals("422", response.getStatusCode());
	}

	@Test
	public void deleteNotOk() {
		EmployeeResponse deleteEmployee = employeeService.deleteEmployee(null);
		assertEquals(deleteEmployee.getStatusCode(), "422");
	}

	@Test
	public void deleteOK() {
		Optional<Employee> employee = Optional.of(getEmployee());
		when(employeeRepository.findById(1)).thenReturn(employee);
		EmployeeResponse deleteEmployee = employeeService.deleteEmployee(1);
		assertEquals("200", deleteEmployee.getStatusCode());
	}
}
