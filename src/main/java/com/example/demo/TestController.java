package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Employee;
import com.example.Model.Regular_Employee;

@RestController
@RequestMapping("/api")
public class TestController {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

	SessionFactory factory = meta.getSessionFactoryBuilder().build();


	@GetMapping("/test1")
	public String getPath() {
		Employee e1 = new Employee();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		//e1.setId(102);
		e1.setFirstName("Gaurav");
		e1.setLastName("Chawla");
		// session.save(e1);
		session.saveOrUpdate(e1);
//	    session.update(e1);  
		t.commit();
		session.close();
		System.out.println("successfully saved");
		return "successfully saved";
	}

	@GetMapping("/regemp")
	public String SaveRegularEmployee() {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Regular_Employee e1 = new Regular_Employee();
		e1.setFirstName("Anuj");
		e1.setLastName("Manocha");
		e1.setBonus(1000);
		e1.setSalary(10000);
		// session.save(e1);
		session.saveOrUpdate(e1);
//	    session.update(e1);  
		t.commit();
		System.out.println("successfully saved");
//		factory.close();
		session.close();
		return "successfully saved";
	}
}
