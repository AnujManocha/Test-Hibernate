package com.example.demo;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.manytoOne.Address1;
import com.example.manytoOne.Employee1;
@RestController
@RequestMapping("/manytoone")
public class ManyToOneController {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

	SessionFactory factory = meta.getSessionFactoryBuilder().build();

	private static Logger logger = Logger.getLogger(ManyToOneController.class);
	@PostMapping("/save")
	private ResponseEntity<Object> saveEmployee(@RequestBody Employee1 emp) {

		logger.info(emp);
		Address1 add = new Address1();
		add.setCity("kaithal");
		add.setState("haryana");
		emp.setAddress(add);
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(emp);
		session.getTransaction().commit();
		session.close();
		logger.debug("saved");
		logger.info("saved");
		
		session.beginTransaction();

		return new ResponseEntity<Object>(emp, HttpStatus.OK);

	}
}
