package com.example.manytoOne;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employee1")
public class Employee1 {

	@Id
	@GeneratedValue
	@Column(name = "emp_id")
	private int id;
	@Column(name = "emp_name")
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address1 address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address1 getAddress() {
		return address;
	}

	public void setAddress(Address1 address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee1 [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
