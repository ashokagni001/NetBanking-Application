package com.i2i.netbankingApplication.hibernateConnection;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
	private HibernateConnection() {
	}
	     
    private Configuration configuration = null;
	private SessionFactory sessionFactory = null;
	private static HibernateConnection hibernateConnection = null;
	
	public static HibernateConnection getInstance() {
	    if (hibernateConnection == null) {
	        hibernateConnection = new HibernateConnection();
	    }
	    return hibernateConnection;
	    }
	
	public Configuration getConfiguration() {
	    try {
	        if(configuration == null) {
	            configuration = new Configuration();
		        configuration.configure("hibernate.cfg.xml"); 
		    }
	    } catch(HibernateException e) {
	             System.err.println("configuration not found Exception" + e);
	    }
	        return configuration;
	}
	
    public SessionFactory getSessionFactory() {
	        try {
	            if(sessionFactory == null) {
	                sessionFactory = configuration.buildSessionFactory();
	           }
	        } catch(HibernateException e) {
	             System.err.println("sessionFactory not found Exception" + e);
	        }
	        return sessionFactory;
	    }
	}


