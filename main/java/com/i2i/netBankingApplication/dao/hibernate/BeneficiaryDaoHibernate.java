package com.i2i.netBankingApplication.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.i2i.netBankingApplication.dao.BeneficiaryDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Beneficiary;

@Repository("beneficiaryDao")
public class BeneficiaryDaoHibernate extends GenericDaoHibernate<Beneficiary, Long> implements BeneficiaryDao{
    
    public BeneficiaryDaoHibernate() {
        super(Beneficiary.class);
    }
     
    /**
     * {@inheritDoc}
     */
    public void insertBeneficiary(Beneficiary beneficiary) throws DataBaseException {
        try {
            getSession().save(beneficiary); 
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE INSERT VALID IFSC..");  
        }
    }
    
    public List<Beneficiary> retrieveAllBeneficiaries() throws DataBaseException {
        try {
            return getSession().createQuery("FROM Beneficiary").list();
        } catch (HibernateException e) {
            throw new DataBaseException("ACCOUNTS IS NOT AVAILABLE.");
        }
    }

    public void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException {
		try {
			Session session = getSession();
			Beneficiary beneficiary = (Beneficiary) session.get(Beneficiary.class, beneficiaryId);
			beneficiary.setStatus("Success");
			session.update(beneficiary);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
		}
	}

	public void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException {
		try {
			Session session = getSession();
			Beneficiary beneficiary = (Beneficiary) session.get(Beneficiary.class, beneficiaryId);
			beneficiary.setStatus("Failure");
			session.update(beneficiary);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
	    }
    }
}
