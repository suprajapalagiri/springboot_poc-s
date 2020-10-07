package com.ojas.employee.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ojas.employee.model.Employee;
import com.ojas.employee.repository.EmployeeRepository;
import com.ojas.employee.response.EmployeeResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public EmployeeResponse saveorupdateEmployee(Employee employee) {

		log.debug("Incoming request coming in to the Employee service :" + employee);
		EmployeeResponse response = null;
		try {
			if (employee == null) {
				log.error("Record not saved");
				response = new EmployeeResponse();
				response.setStatusCode("422");
				response.setMessage("Employee Record not saved");
				response.setData(null);
			} else {
				response = new EmployeeResponse();
				log.error("Record saved");
				Employee saveEmployee = employeeRepo.save(employee);
				response.setStatusCode("200");
				response.setMessage("Employee Record saved Successfully");
				response.setData(saveEmployee);
			}
		} catch (Exception e) {
			response = new EmployeeResponse();
			response.setStatusCode("409");
			response.setMessage("please provide proper data");
			response.setData(null);
		}
		return response;
	}

	@Override
	public EmployeeResponse getById(Integer id) {
		log.debug("Request coming in to the Employee service id method : " + id);
		EmployeeResponse response = null;
		Optional<Employee> findById = employeeRepo.findById(id);
		if (!findById.isPresent()) {
			log.error("Record not found");
			response = new EmployeeResponse();
			response.setStatusCode("422");
			response.setMessage("Record not found");
			response.setData(findById);
		} else {

			response = new EmployeeResponse();
			response.setStatusCode("200");
			response.setMessage("data fetched succesfully");
			response.setData(findById);

		}
		return response;
	}

	@Override
	public EmployeeResponse getAllEmployee(Integer pageNo, Integer pageSize) {
		log.debug("Request coming in to the Employee service GetAll method ");
		EmployeeResponse response = null;
		Sort sort = Sort.by("id").ascending();

		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		List<Employee> emp = employeeRepo.findAll(page).toList();
		if (emp.isEmpty()) {
			log.error("Record not found");
			response = new EmployeeResponse();
			response.setStatusCode("422");
			response.setMessage("Record not found");
			response.setData(null);
		} else {
			log.debug("Records found successfully");
			response = new EmployeeResponse();
			response.setStatusCode("200");
			response.setMessage("Records get successfully");
			response.setData(emp);
		}
		return response;
	}

	@Override
	public EmployeeResponse deleteEmployee(Integer id) {
		log.debug("Request coming in to the Employee service delete method ");
		EmployeeResponse response = null;
		Optional<Employee> findById = employeeRepo.findById(id);
		if (!findById.isPresent()) {
			log.error("Record not found");
			response = new EmployeeResponse();
			response.setStatusCode("422");
			response.setMessage("Record not found");
			response.setData(findById);
		} else {
			employeeRepo.delete(findById.get());
			log.debug("record deleted");
			response = new EmployeeResponse();
			response.setStatusCode("200");
			response.setMessage("Record deleted succesfully");
			response.setData(findById);
		}
		return response;
	}

}
