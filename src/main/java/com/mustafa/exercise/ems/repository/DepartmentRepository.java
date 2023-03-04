package com.mustafa.exercise.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mustafa.exercise.ems.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	boolean existsByName(String name);

	Department findByName(String name);

}
