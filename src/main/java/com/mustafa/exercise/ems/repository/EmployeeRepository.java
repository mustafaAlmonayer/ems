package com.mustafa.exercise.ems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mustafa.exercise.ems.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e where e.department.id = :departmentId and e.id = :employeeId")
	Optional<Employee> findByIdAndDepartmentId(@Param(value = "employeeId") Long employeeId, @Param(value = "departmentId") Long departmentId);

	Optional<List<Employee>> findAllByDepartmentId(Long departmentId);
	
}
