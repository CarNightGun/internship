package com.km.test.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest
{
	Configuration conf;
	ServiceRegistry sRegistry;
	SessionFactory sfFactory;
	Session session;
	Transaction tx;

	@Before
	public void setUp() throws Exception
	{
		conf = new Configuration().configure("config/hibernate.cfg.xml");
	
		sRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

		sfFactory = conf.buildSessionFactory(sRegistry);
		
		session = sfFactory.openSession();

		tx = session.beginTransaction();
	}

	@After
	public void tearDown() throws Exception
	{
		tx.commit();
		session.close();
		sfFactory.close();
	}

	@Test
	public void test()
	{
		
	}

}
