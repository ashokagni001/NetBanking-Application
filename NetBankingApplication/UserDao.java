package com.netbanking.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.netbanking.connection.HibernateConnection;
import com.netbanking.exception.UserException;
import com.netbanking.model.User;

public class UserDao {
	SessionFactory sessionFactory = HibernateConnection.getSessionFactory();

	public void insertUser(User user) throws UserException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            System.out.println(user);
            session.save(user);
            transaction.commit();
        } catch(Exception e) {
        	e.printStackTrace();
            throw new UserException("Your data was not inserted. Please try again", e);
        } finally {
            session.close();
        }
    }
}
