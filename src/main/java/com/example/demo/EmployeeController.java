package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Address;
import com.example.Model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

	SessionFactory factory = meta.getSessionFactoryBuilder().build();

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.persist(employee.getAddress());
			session.persist(employee);
		} catch (Exception e) {

		}
		t.commit();
		session.close();
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}

	@GetMapping("/get/{id}")
	private ResponseEntity<Employee> getEmployee(@PathVariable int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Employee emp =(Employee) session.get(Employee.class, id);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
}
