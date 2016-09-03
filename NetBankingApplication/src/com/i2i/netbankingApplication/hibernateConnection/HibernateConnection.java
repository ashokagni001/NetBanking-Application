package com.i2i.netbankingApplication.hibernateConnection;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateConnection {
	private HibernateConnection() {
	}
	     
    private AnnotationConfiguration AnnotationConfiguration = null;
	private SessionFactory sessionFactory = null;
	private static HibernateConnection hibernateConnection = null;
	
	public static HibernateConnection getInstance() {
	    if (hibernateConnection == null) {
	        hibernateConnection = new HibernateConnection();
	    }
	    return hibernateConnection;
	    }
	
	public AnnotationConfiguration getAnnotationConfiguration() {
	    try {
	        if(AnnotationConfiguration == null) {
	            AnnotationConfiguration = new AnnotationConfiguration();
		        AnnotationConfiguration.configure("hibernate.cfg.xml"); 
		    }
	    } catch(HibernateException e) {
	             System.err.println("AnnotationConfiguration not found Exception" + e);
	    }
	        return AnnotationConfiguration;
	}
	
    public SessionFactory getSessionFactory() {
	        try {
	            if(sessionFactory == null) {
	                sessionFactory = AnnotationConfiguration.buildSessionFactory();
	           }
	        } catch(HibernateException e) {
	             System.err.println("sessionFactory not found Exception" + e);
	        }
	        return sessionFactory;
	    }
	}


