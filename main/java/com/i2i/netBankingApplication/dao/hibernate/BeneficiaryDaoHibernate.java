package com.i2i.netBankingApplication.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
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
    
    public List<Beneficiary> getAllBeneficiaries() throws DataBaseException {
        try {
            return getSession().createQuery("FROM Beneficiary").list();
        } catch (HibernateException e) {
            throw new DataBaseException("ACCOUNTS IS NOT AVAILABLE.");
        }
    }
}
