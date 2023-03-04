package com.mustafa.exercise.ems.repository;

import com.mustafa.exercise.ems.entity.Department;
import com.mustafa.exercise.ems.entity.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByIdAndDepartment(Long id, Department department);
}