package com.mustafa.exercise.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.exception.ResourceNotFoundExceptionGet;
import com.mustafa.exercise.ems.repository.DepartmentRepository;

@Service("DepartmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	public ResponseEntity<List<Department>> getDepartments() {
		List<Department> departmentList = departmentRepository.findAll();
		return new ResponseEntity<>(departmentList, HttpStatus.OK);
	}

	public ResponseEntity<Department> getDepartment(Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Department With ID: " + id + " Not Found");
		}
		Department responceDepartment = department.get();
		return new ResponseEntity<>(responceDepartment, HttpStatus.OK);
	}

	public ResponseEntity<Department> saveDepartment(Department department) {
		if (departmentRepository.existsByName(department.getName())) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Department savedDepartment = departmentRepository.save(department);
		return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Department> updateDepartment(Department department) {
		if (!departmentRepository.existsById(department.getId())) {
			throw new ResourceNotFoundExceptionGet("Department With ID: " + department.getId() + " Not Found");
		};
		departmentRepository.save(department);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteDepartment(Long id) {
		if (!departmentRepository.existsById(id)) {
			throw new ResourceNotFoundExceptionGet("Department With ID: " + id + " Not Found");
		}
		departmentRepository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
