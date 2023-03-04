package com.mustafa.exercise.ems.controller;

import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
		return employeeService.getEmployee(id);
	}

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);
	}

	@DeleteMapping("{id}/delete")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
		return employeeService.deleteEmployee(id);
	}

}
