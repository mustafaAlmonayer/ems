package com.mustafa.exercise.ems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mustafa.exercise.ems.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByIdAndDepartmentId(Long emmployeeId, Long departmentId);
	List<Employee> findAllByDepartmentId(Long departmentId);
}
