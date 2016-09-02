package com.i2i.netbankingApplication.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.i2i.netbankingApplication.hibernateConnection.HibernateConnection;
import com.i2i.netbankingApplication.model.Branch;

public class BranchDao {
	 HibernateConnection hibernateConnectionObject  = HibernateConnection.getInstance();	
	 Configuration configuration = hibernateConnectionObject.getConfiguration();
	 SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
	 
	 public void addBranch(Branch branch) {
	     Session session = sessionFactory.openSession();
	     Transaction transaction = null;
	     try {
	         transaction = session.beginTransaction();
		     session.persist(branch); 
	         transaction.commit();                                                                    
		 } catch (HibernateException e) {
	     } finally {
	         session.close(); 
	     }
	 }
	 
	 public List<Branch> retriveBranchs() {
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            List<Branch> branchs = session.createQuery("from Branch").list();
	            transaction.commit();
	            return branchs;
	        } catch(HibernateException e) {
	        } finally {
	            session.close();
	        }
			return null;
	    }
	 
	 
}
