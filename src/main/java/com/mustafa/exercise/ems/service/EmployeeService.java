package com.mustafa.exercise.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.exception.ResourceNotFoundExceptionGet;
import com.mustafa.exercise.ems.exception.ResourceNotFoundExceptionDelete;
import com.mustafa.exercise.ems.repository.DepartmentRepository;
import com.mustafa.exercise.ems.repository.EmployeeRepository;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employeeList = employeeRepository.findAll();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}

	public ResponseEntity<Employee> getEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Employee Not Found ID: " + id);
		}
		return new ResponseEntity<>(employee.get(), HttpStatus.OK);
	}

	public ResponseEntity<Employee> saveEmployee(Employee employee) {
		Department department;
		if (!departmentRepository.existsByName(employee.getDepartment().getName())) {
			department = departmentRepository.save(employee.getDepartment());
		} else {
			department = departmentRepository.findByName(employee.getDepartment().getName());
			employee.setDepartment(department);
		}
		employeeRepository.save(employee);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	public ResponseEntity<Employee> deleteEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundExceptionDelete("Employee Not Found ID: " + id);
		}
		employeeRepository.delete(employee.get());
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}

}
