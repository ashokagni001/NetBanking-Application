package com.i2i.netbankingApplication.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.hibernateConnection.HibernateConnection;
import com.i2i.netbankingApplication.model.Role;
import com.i2i.netbankingApplication.model.UserRole;

public class RoleDao {
	HibernateConnection hibernateConnectionObject = HibernateConnection.getInstance();
	Configuration configuration = hibernateConnectionObject.getConfiguration();
	SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();

	public void insertRole(UserRole userRole) throws DataBaseException {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			System.out.println("Rows affected: " + userRole);
			session.save(userRole);
			System.out.println("Rows affected: " + userRole);
			transaction.commit();
		} catch (HibernateException exp) {
			throw new DataBaseException("ROLEDAO/ Sorry information can't save please try again" + exp);
		} finally {
			session.close();
		}
	}

	public List<Role> retriveAllRole() throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			return session.createQuery("FROM Role").list();
		} catch (HibernateException exp) {
			throw new DataBaseException("ROLEDAO/ DATA IS NOT AVAILABLE.INSERT DATA." + exp);
		} finally {
			session.close();
		}
	}

	public Role retrieveRoleById(String id) throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			return (Role) session.get(Role.class, id);
		} catch (HibernateException e) {
			System.out.println(e);
			throw new DataBaseException("ROLEDAO/ CHECK ID " + id + "PLEASE INSERT VALID ID...\n");
		} finally {
			session.close();
		}
	}

	public List<UserRole> retriveAllUserRole() throws DataBaseException {
		Session session = sessionFactory.openSession();
		try {
			System.out.println("dao userrole");
			return session.createQuery("FROM UserRole").list();
		} catch (HibernateException e) {
			System.out.println(e);
			throw new DataBaseException("ROLEDAO/ Data is not available please insert the data." + e);
		} finally {
			session.close();
		}
	}

}
