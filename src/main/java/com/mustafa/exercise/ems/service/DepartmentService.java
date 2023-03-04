package com.mustafa.exercise.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.databind.ser.FilterProvider;
//import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.exception.ResourceNotFoundExceptionGet;
import com.mustafa.exercise.ems.repository.DepartmentRepository;
import com.mustafa.exercise.ems.repository.EmployeeRepository;

@Service("DepartmentService")
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	

	public ResponseEntity<List<Department>> getDepartments() {
		List<Department> departmentList = departmentRepository.findAll();
//		MappingJacksonValue mappingJacksonValue = applyFilter(departmentList, "name", "id", "employees");
		return new ResponseEntity<>(departmentList, HttpStatus.OK);
	}

	public ResponseEntity<Department> getDepartment(Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Department With ID: " + id + " Not Found");
		}
		Department responceDepartment = department.get();
//        MappingJacksonValue mappingJacksonValue = applyFilter(responceDepartment, "name");
		return new ResponseEntity<>(responceDepartment, HttpStatus.OK);
	}

	public ResponseEntity<Department> saveDepartment(Department department) {
		if (departmentRepository.existsByName(department.getName())) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Department savedDepartment = departmentRepository.save(department);
//        MappingJacksonValue mappingJacksonValue = applyFilter(savedDepartment, "name", "id");
		return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	}

	public ResponseEntity<List<Employee>> getDepartmentEmployees(Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		if (department.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Department With ID: " + id + " NotFound");
		}
		return new ResponseEntity<>(department.get().getEmployees(), HttpStatus.OK);
	}

	public ResponseEntity<Employee> getDepartmentEmployee(Long departmentID, Long EmployeeId) {
		Optional<Department> department = departmentRepository.findById(departmentID);
		if (department.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Department With ID: " + departmentID + " NotFound");
		}
		Optional<Employee> employee = employeeRepository.findByIdAndDepartment(EmployeeId, department.get());
		if (employee.isEmpty()) {
			throw new ResourceNotFoundExceptionGet("Employee With ID: " + EmployeeId + " NotFound");
		}
		return new ResponseEntity<>(employee.get(), HttpStatus.OK);
	}

//	private MappingJacksonValue applyFilter(Object object, String...wantedFields ) {
//		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(wantedFields);
//		FilterProvider filters = new SimpleFilterProvider().addFilter("DepartmentFilter", filter);
//		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);
//		mappingJacksonValue.setFilters(filters);
//		return mappingJacksonValue;
//	}

}
