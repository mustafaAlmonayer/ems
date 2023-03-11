package com.mustafa.exercise.ems.controller;

import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
	}
	
	@GetMapping("/departments/{id}/employees/all")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable Long id) {
		return new ResponseEntity<>(employeeService.getEmployees(id), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
	}
	

	@GetMapping("/departments/{departmentId}/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployeeByDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
		return new ResponseEntity<>(employeeService.getEmployee(employeeId, departmentId), HttpStatus.OK);
	}

	@PostMapping("/employees/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
	}
	
	@PutMapping("/employees/update")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

	@DeleteMapping("/employees/{id}/delete")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>( HttpStatus.OK);
	}

}
