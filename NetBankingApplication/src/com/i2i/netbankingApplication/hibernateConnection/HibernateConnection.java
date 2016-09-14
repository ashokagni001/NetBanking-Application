package com.i2i.netbankingApplication.hibernateConnection;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * <p>
 *     Class for creating hibernate annotation connection using singleton concept.
 *     Creates configuration and sessionFactory object.
 *     Mapping to the hibernate annotation configuration file.
 * </p> 
 *
 * @author Ashok
 * 
 * @created 2016-08-27 
 */
public class HibernateConnection {
     
    private AnnotationConfiguration configuration = null;
    private SessionFactory sessionFactory = null;
    private static HibernateConnection hibernateConnection = null;
     
    /**
	 * <p>
     *     Default Constructor.
     *     It is private type. so no need create for outside object.
     * </p>
     */
    private HibernateConnection() {
    }
    

    /**
	 * Create the hibernate Connection.
	 * 
	 * @return hibernateConnection
	 *     Return a Connection object.
	 */
    public static HibernateConnection getInstance() {
        if (hibernateConnection == null) {
            hibernateConnection = new HibernateConnection();
        }
        return hibernateConnection;
    }
    

    /**
	 * Create a AnnotationConfiguration and connect the hibernate configuration file.
	 * 
	 * @return configuration
	 *     Return a annotation Configuration object.
	 */
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
    
    /**
	 * Create the sessionFactory object.
	 * 
	 * @return sessionFactory
	 *     Return a SessionFactory object.
	 */
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
