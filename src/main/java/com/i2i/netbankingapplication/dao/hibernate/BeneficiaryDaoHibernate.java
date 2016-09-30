package com.i2i.netbankingapplication.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.i2i.netbankingapplication.dao.BeneficiaryDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Beneficiary;

/**
 * <p>
 *   DataAccessObject(DAO) which is used to perform insert, update, retrieve all 
 *   operations for beneficiary and return to the responses.
 *   Creates session and transaction objects for each operation
 * </p>
 * 
 * @author Team-2
 * 
 * @created 2016-09-26
 */
@Repository("beneficiaryDao")
@Transactional
public class BeneficiaryDaoHibernate extends GenericDaoHibernate<Beneficiary, Long> implements BeneficiaryDao{
    
    /**
     * Constructor that sets the entity to Beneficiary.class.
     */
    public BeneficiaryDaoHibernate() {
        super(Beneficiary.class);
    }
     
    /**
     * <p>
     *     Saves a user's beneficiary information(insert new beneficiary account).
     * </p>
     * 
     * @param beneficiary 
     *     beneficiary holds the information of beneficiary.The beneficiary to save.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like NullPointerException,
     *     NumberFormatException, HibernateException.
     */
    public void insertBeneficiary(Beneficiary beneficiary) throws DataBaseException {
        try {
            getSession().save(beneficiary); 
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE INSERT VALID IFSC..");  
        }
    }
    
    /**
     * </p>
     *     Gets a list of beneficiaries.
     * </p>    
     *  
     * @return Beneficiaries
     *     The list of beneficiaries in DB. if the beneficiaries is already persisted
     *     else return null.
     * 
     * @throws DataBaseException
     *     If there is an error in getting the object like HibernateException.
     */
    public List<Beneficiary> retrieveAllBeneficiaries() throws DataBaseException {
        try {
            return getSession().createQuery("FROM Beneficiary").list();
        } catch (HibernateException e) {
            throw new DataBaseException("ACCOUNTS IS NOT AVAILABLE.");
        }
    }
    
    public Beneficiary retrievebeneficiaryAccount(int beneficiaryId) throws DataBaseException {
        try {
            return (Beneficiary)getSession().get(Beneficiary.class, beneficiaryId); 
        } catch (HibernateException e) {
            throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
        }
    }
    
    public void updateBeneficiary(Beneficiary beneficiary) throws DataBaseException {
        try {
            getSession().update(beneficiary);
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE INSERT VALID IFSC.."); 
        }
    }
}
