package com.mustafa.exercise.ems.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, length = 56, unique = true)
	@NotNull(message = "cannot be empty")
	@Size(min = 2, max = 56, message = "name size should be more than 1 and less than 57")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Employee> employees;
	
	private Long managerId;

	public Department() {
	}
	
	public Department(Long id, String name,List<Employee> employees, Long managerId) {
		super();
		this.id = id;
		this.name = name;
		this.employees = employees;
		this.managerId = managerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, managerId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(id, other.id) && Objects.equals(managerId, other.managerId)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + ", managerId=" + managerId
				+ "]";
	}
	
}
