package com.mustafa.exercise.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.exception.ResourceExistsException;
import com.mustafa.exercise.ems.exception.ResourceNotFoundException;
import com.mustafa.exercise.ems.repository.DepartmentRepository;

@Service("DepartmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	public List<Department> getDepartments() {
		return departmentRepository.findAll();
	}

	public Department getDepartment(Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		return department.orElseThrow(() -> new ResourceNotFoundException("Department With ID: " + id + " Not Found"));
	}

	public Department saveDepartment(Department department) {
		if (departmentRepository.existsByName(department.getName()))
			throw new ResourceExistsException("Departemnt With Name: " + department.getName() + " Already Exists");
		return departmentRepository.save(department);
	}

	public Department updateDepartment(Department department) {
		if (!departmentRepository.existsById(department.getId()))
			throw new ResourceNotFoundException("Department With ID: " + department.getId() + " Not Found");
		return departmentRepository.save(department);
	}

	public void deleteDepartment(Long id) {
		if (!departmentRepository.existsById(id))
			throw new ResourceNotFoundException("Department With ID: " + id + " Not Found");
		Department department = departmentRepository.findById(id).get();
		department.getEmployees().stream().forEach(t -> t.setDepartment(null));
		department.setEmployees(null);
		department.setManagerId(null);
		departmentRepository.deleteById(id);
	}

}
