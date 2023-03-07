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
	
	public ResponseEntity<List<Employee>> getEmployees(Long departmentId) {
		List<Employee> employeeList = employeeRepository.findAllByDepartmentId(departmentId);
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
	}
	
	public ResponseEntity<Employee> getEmployee(Long employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Employee Not Found ID: " + employeeId);
		}
		return new ResponseEntity<>(employee.get(), HttpStatus.OK);
	}

	public ResponseEntity<Employee> getEmployee(Long employeeId, Long departmentId) {
		Optional<Employee> employee = employeeRepository.findByIdAndDepartmentId(employeeId, departmentId);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Employee Not Found ID: " + employeeId);
		}
		return new ResponseEntity<>(employee.get(), HttpStatus.OK);
	}

	public ResponseEntity<Employee> saveEmployee(Employee employee) {
		 
		if (!departmentRepository.existsByName(employee.getDepartment().getName())) {
			departmentRepository.save(employee.getDepartment());
		} else {
			Optional<Department> department = departmentRepository.findByName(employee.getDepartment().getName());
			employee.setDepartment(department.get());
		}
		employeeRepository.save(employee);
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Employee> updateEmployee(Employee employee) {
		if (!employeeRepository.existsById(employee.getId())) {
			throw new ResourceNotFoundExceptionGet("Employee Not Found ID: " + employee.getId());
		}
		employeeRepository.save(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		
	}

	public ResponseEntity<Employee> deleteEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Employee Not Found ID: " + id);
		}
		employeeRepository.delete(employee.get());
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
