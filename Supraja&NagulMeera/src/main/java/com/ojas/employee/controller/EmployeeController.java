package com.ojas.employee.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.employee.model.Employee;
import com.ojas.employee.response.EmployeeResponse;
import com.ojas.employee.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeService;

	Logger log = Logger.getLogger(this.getClass());

	@PostMapping("/saveorupdate")
	public ResponseEntity<Object> saveorupdate(@Valid @RequestBody Employee employee) {
		EmployeeResponse saveorupdateEmployee = employeeService.saveorupdateEmployee(employee);
		if (saveorupdateEmployee == null) {
			return new ResponseEntity<Object>(saveorupdateEmployee, HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<Object>(saveorupdateEmployee, HttpStatus.OK);

		}
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id) {
		if (id == null) {
			log.debug("please provide Id");
			return new ResponseEntity<Object>(id, HttpStatus.CONFLICT);
		} else {
			EmployeeResponse getById = employeeService.getById(id);
			return new ResponseEntity<Object>(getById, HttpStatus.OK);
		}
	}

	@GetMapping("/getAll/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getAll(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
		if (pageNo == null || pageSize == null) {
			return new ResponseEntity<Object>("no records", HttpStatus.CONFLICT);
		}
		EmployeeResponse getAllEmployee = employeeService.getAllEmployee(pageNo, pageSize);
		return new ResponseEntity<Object>(getAllEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) {
		if (id == null) {
			log.debug("please provide Id");
			return new ResponseEntity<Object>(id, HttpStatus.CONFLICT);
		} else {
			EmployeeResponse deleteEmployee = employeeService.deleteEmployee(id);
			return new ResponseEntity<Object>(deleteEmployee, HttpStatus.OK);
		}
	}
}
