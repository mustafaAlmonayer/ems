package com.mustafa.exercise.ems.controller;

import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employee/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getEmployees();
	}
	
	@GetMapping("/department/{id}/employee/all")
	public ResponseEntity<List<Employee>> getAllEmployeesByDepartment(@PathVariable Long id) {
		return employeeService.getEmployees(id);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		return employeeService.getEmployee(id);
	}
	

	@GetMapping("/department/{departmentId}/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeByDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
		return employeeService.getEmployee(employeeId, departmentId);
	}

	@PostMapping("/employee/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@DeleteMapping("/employee/{id}/delete")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployee(id);
	}

}
