package com.mustafa.exercise.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@GetMapping("/all")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
		return new ResponseEntity<>(departmentService.getDepartment(id), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department) {
		return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Department> updateDepartment(@Valid @RequestBody Department department) {
		return new ResponseEntity<>(departmentService.updateDepartment(department), HttpStatus.OK);

	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
