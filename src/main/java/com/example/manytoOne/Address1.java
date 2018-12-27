package com.example.manytoOne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address1")
public class Address1 {

	@Id
	@GeneratedValue
	@Column(name = "add_id")
	private int id;
	@Column(name = "state_name")
	private String state;
	@Column(name = "city_name")
	private String city;

	@OneToOne
	private Employee1 employee;

	@Override
	public String toString() {
		return "Address1 [id=" + id + ", state=" + state + ", city=" + city + ", employee=" + employee + "]";
	}

	public Employee1 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee1 employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
