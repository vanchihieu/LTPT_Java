package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entity.Department;
import entity.Doctor;
import entity.Patient;
import entity.Person;
import entity.Treatment;

public class MySessionFactory {
	private static MySessionFactory instance;
	private SessionFactory sessionFactory;
	
	private MySessionFactory() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		.configure()//hibernate.cfg.xml
		.build();
		
		Metadata metadataSourse = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(Patient.class)
				.addAnnotatedClass(Doctor.class)
				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(Treatment.class)
				.addAnnotatedClass(Department.class)
				.getMetadataBuilder()
				.build();
				
		sessionFactory = metadataSourse
				.getSessionFactoryBuilder()
				.build();
	}
	
	public synchronized static MySessionFactory getInstance() {
		if(instance == null)
			instance = new MySessionFactory();
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
