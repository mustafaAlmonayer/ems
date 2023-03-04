package com.mustafa.exercise.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/all")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return departmentService.getDepartments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
		return departmentService.getDepartment(id);
	}

	@PostMapping("/save")
	public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
		return departmentService.saveDepartment(department);
	}

	@GetMapping("/{id}/employees/all")
	public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable Long id) {
		return departmentService.getDepartmentEmployees(id);
	}

	@GetMapping("/{departmentID}/employee/{employeeID}")
	public ResponseEntity<Employee> getEmployeeByDepartment(@PathVariable Long departmentID,
			@PathVariable Long employeeID) {
		return departmentService.getDepartmentEmployee(departmentID, employeeID);
	}

}
