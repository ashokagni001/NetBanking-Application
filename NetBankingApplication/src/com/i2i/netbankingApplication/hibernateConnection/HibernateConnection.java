package com.i2i.netbankingApplication.hibernateConnection;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
<<<<<<< HEAD
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


=======



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
            if(configuration == null) {
               configuration = new AnnotationConfiguration();
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
             System.err.println("sessionFactory not found Exception----->" + e);
        }
        return sessionFactory ;
    }
}
>>>>>>> ed69c9f4b0a48e1073ed04df1fb4663fa79f4f68
