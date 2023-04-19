package com.dam.library.model;

import javax.persistence.*;

@Entity
@Table(name = "librarian")
public class Librarian {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Column(unique = true, nullable = false)
	private int empId;
	@Column(unique = true, nullable = false)
	private String password;

	Librarian(){}
	
	public Librarian(String name, int empId, String password) {
		super();
		this.name = name;
		this.empId = empId;
		this.password = password;
	}
	public int getUniqueId() {
		return empId;
	}

	public void setUniqueId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Librarian [id = " + id + ", name = " + name + ", Librarian Id = " + empId
				+ "]";
	}
	
	
	
	
}
