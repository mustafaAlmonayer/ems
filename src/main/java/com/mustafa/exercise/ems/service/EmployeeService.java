package com.mustafa.exercise.ems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.entity.Employee;
import com.mustafa.exercise.ems.exception.ResourceNotFoundException;
import com.mustafa.exercise.ems.repository.DepartmentRepository;
import com.mustafa.exercise.ems.repository.EmployeeRepository;

@Service("EmployeeService")
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public List<Employee> getEmployees(Long departmentId) {
		return employeeRepository.findAllByDepartmentId(departmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found Department ID: " + departmentId));
	}

	public Employee getEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found ID: " + employeeId));
	}

	public Employee getEmployee(Long employeeId, Long departmentId) {
		return employeeRepository.findByIdAndDepartmentId(employeeId, departmentId)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Employee Not Found Employee ID, Department ID:  " + employeeId + ", " + departmentId));
	}

	public Employee saveEmployee(Employee employee) {
		if (departmentRepository.existsByName(employee.getDepartment().getName())) {
			Optional<Department> department = departmentRepository.findByName(employee.getDepartment().getName());
			employee.setDepartment(department.get());
		}
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		if (!employeeRepository.existsById(employee.getId()))
			throw new ResourceNotFoundException("Employee Not Found ID: " + employee.getId());
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isEmpty())
			throw new ResourceNotFoundException("Employee Not Found ID: " + id);
		employeeRepository.delete(employee.get());
	}

}
