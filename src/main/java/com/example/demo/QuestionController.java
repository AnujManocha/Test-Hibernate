package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Answer;
import com.example.Model.Question;
import com.example.Model.Question2;

@RestController
@RequestMapping("/api2")
public class QuestionController {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

	SessionFactory factory = meta.getSessionFactoryBuilder().build();

	@GetMapping("/saveq")
	public String saveQuestion() {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("Java is a programming language");
		list1.add("Java is a platform");

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Servlet is an Interface");
		list2.add("Servlet is an API");

		Question question1 = new Question();
		question1.setQname("What is Java?");
		question1.setAnswers(list1);

		Question question2 = new Question();
		question2.setQname("What is Servlet?");
		question2.setAnswers(list2);

		// session.persist(question1);
		// session.persist(question2);
		session.save(question1);
		t.commit();
		session.close();
		System.out.println("successfully saved");
		return "successfully saved";
	}
	@GetMapping("/ques")
	public ResponseEntity<Object> getQuestions() {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		TypedQuery query = session.createQuery("from Question");
		List<Question> list = query.getResultList();
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
	//for one to all
	
	@GetMapping("/saveQA")
	public ResponseEntity<Object> saveQuestionsAndAnswers() {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		   Answer ans1=new Answer();    
		    ans1.setAnswername("Java is a programming language");    
		    ans1.setPostedBy("Ravi Malik");    
		        
		    Answer ans2=new Answer();    
		    ans2.setAnswername("Java is a platform");    
		    ans2.setPostedBy("Sudhir Kumar");    
		        
		    Answer ans3=new Answer();    
		    ans3.setAnswername("Servlet is an Interface");    
		    ans3.setPostedBy("Jai Kumar");    
		        
		    Answer ans4=new Answer();    
		    ans4.setAnswername("Servlet is an API");    
		    ans4.setPostedBy("Arun");    
		        
		    ArrayList<Answer> list1=new ArrayList<Answer>();    
		    list1.add(ans1);    
		    list1.add(ans2);    
		        
		    ArrayList<Answer> list2=new ArrayList<Answer>();    
		    list2.add(ans3);    
		    list2.add(ans4);  
		    
		    Question2 question1=new Question2();    
		    question1.setQname("What is Java?");    
		    question1.setAnswers(list1);    
		        
		    Question2 question2=new Question2();    
		    question2.setQname("What is Servlet?");    
		    question2.setAnswers(list2);    
		    
		    
		    session.persist(question1);    
		    session.persist(question2);    
			t.commit();
			session.close();
		return new ResponseEntity<Object>(question1, HttpStatus.OK);
	}
}
