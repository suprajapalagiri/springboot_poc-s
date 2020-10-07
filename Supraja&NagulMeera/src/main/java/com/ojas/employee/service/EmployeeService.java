package com.ojas.employee.service;

import org.springframework.stereotype.Service;

import com.ojas.employee.model.Employee;
import com.ojas.employee.response.EmployeeResponse;

@Service
public interface EmployeeService {
	public EmployeeResponse saveorupdateEmployee(Employee employee);

	public EmployeeResponse getById(Integer id);

	public EmployeeResponse getAllEmployee(Integer pageNo, Integer pageSize);

	public EmployeeResponse deleteEmployee(Integer id);
}
