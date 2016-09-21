package com.i2i.netbankingApplication.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.hibernateConnection.HibernateConnection;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;


/**
 * <p>
 *     When request comes from BranchService. BranchDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to CustomerService.
 *     It handles the HibernateException.
 *     Connect to the HibernateConnection class use to create hibernate connection.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
public class BranchDao {

    /**
     * <p>
     *     Connect to the HibernateConnection class use to create hibernate connection.
     *     and create SessionFactory.Return the new session object. 
     * </p>
     * 
     * @return Session
     *     Return the new session object. 
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    private Session hibernateConncetion() throws DataBaseException {
        try {
            HibernateConnection hibernateConnectionObject = HibernateConnection.getInstance();
            Configuration configuration = hibernateConnectionObject.getConfiguration();
            SessionFactory sessionFactory = hibernateConnectionObject.getSessionFactory();
            return sessionFactory.openSession();
        } catch (DataBaseException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Get the branch object from BranchService and add Branch to database. 
     * 
     * @param branch
     *     object of Branch to add.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void insertBranch(Branch branch) throws DataBaseException {
        Session session = hibernateConncetion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(branch);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE INSERT VALID IFSC..");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Get the IFSCode from BranchService.
     *     Delete branch from database. 
     * </p>
     * 
     * @param IFSCode
     *     IFSCode of Branch.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void deleteBranchById(String IFSCode) throws DataBaseException {
        Session session = hibernateConncetion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Branch branch = (Branch) session.get(Branch.class, IFSCode);
            session.delete(branch);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DataBaseException("CHECK IFSC " + IFSCode + "PLEASE INSERT VALID IFSC...\n");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Get the IFSCode from BranchService.
     *     Retrieves Branch data from database and returns branch object to BranchService.
     * </p>
     * 
     * @param IFSCode
     *     IFSCode of Branch to view.
     *     
     * @return branch
     *     Object of branch class.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public Branch retrieveBranchById(String IFSCode) throws DataBaseException {
        Session session = hibernateConncetion();
        try {
            return ((Branch) session.get(Branch.class, IFSCode));
        } catch (HibernateException e) {
            throw new DataBaseException("CHECK IFSC : " + IFSCode + "PLEASE INSERT VALID IFSC.");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Retrieves all Branch from database.
     *     Return all branches in List type.
     * </p>
     * 
     * @return list
     *     Return the list of Branches.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public List < Branch > retriveAllBranches() throws DataBaseException {
        Session session = hibernateConncetion();
        try {
            return session.createQuery("FROM Branch").list();
        } catch (HibernateException e) {
            throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Get the address object from BranchService and add Branch Address to database. 
     *     Get the customerId from BranchService and update address in Branch to database.
     * </p>
     * 
     * @param IFSCode
     *     IFSCode of branch
     * @param address
     *     object of Address class to add.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void insertAddress(String IFSCode, Address address) throws DataBaseException {
        Session session = hibernateConncetion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Branch branch = (Branch) session.get(Branch.class, IFSCode);
            session.save(address);
            branch.setAddress(address);
            session.update(branch);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DataBaseException("DATA IS NOT AVAILABLE.INSERT DATA.");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Get the address Id from BranchService.
     *     Retrieves Branch address from database and returns Address object to BranchService.
     * </p>
     * 
     * @param addressId
     *     addressId of Address to Add.
     *     
     * @return object
     *     Return the object of Address class.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public Address retrieveAddressById(int addressId) throws DataBaseException {
        Address address;
        Session session = hibernateConncetion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            address = (Address) session.get(Address.class, addressId);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
        } finally {
            session.close();
        }
        return address;
    }

    /**
     * Get the account object from BranchService and add Branch database. 
     * 
     * @param account
     *     object of account class to add.
     *     
     * @return message
     *     Return Status message(Success or failure).
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void insertAccount(Account account) throws DataBaseException {
        Session session = hibernateConncetion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE CHECK YOUR DATAS " + account.getAccountNumber() + " YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Retrieves all accounts from database.
     *     Return all accounts in List type.
     * </p>
     * 
     * @return list
     *     Returns the list of Accounts.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public List < Account > retriveAllAccounts() throws DataBaseException {
        Session session = hibernateConncetion();
        try {
            return session.createQuery("from Account").list();
        } catch (HibernateException e) {
            throw new DataBaseException("RECORD IS NOT AVAILABLE.INSERT RECORD.");
        } finally {
            session.close();
        }
    }
}
