package com.i2i.netbankingApplication.hibernateConnection;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class HibernateConnection {

	private AnnotationConfiguration configuration = null;
	private SessionFactory sessionFactory = null;
	private static HibernateConnection hibernateConnection = null;

	private HibernateConnection() {
	}

	public static HibernateConnection getInstance() {
		if (hibernateConnection == null) {
			hibernateConnection = new HibernateConnection();
		}
		return hibernateConnection;
	}

	public AnnotationConfiguration getConfiguration() {
		try {
			if (configuration == null) {
				configuration = new AnnotationConfiguration();
				configuration.configure("hibernate.cfg.xml");
			}
		} catch (HibernateException e) {
			System.err.println("configuration not found Exception" + e);
		}
		return configuration;
	}

	public SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = configuration.buildSessionFactory();
			}
		} catch (HibernateException e) {
			System.err.println("sessionFactory not found Exception----->" + e);
		}
		return sessionFactory;
	}
}
