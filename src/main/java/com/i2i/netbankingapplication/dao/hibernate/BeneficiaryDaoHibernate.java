package com.i2i.netbankingapplication.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.i2i.netbankingapplication.dao.BeneficiaryDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Beneficiary;



@Repository("beneficiaryDao")
public class BeneficiaryDaoHibernate extends GenericDaoHibernate < Beneficiary, Long > implements BeneficiaryDao {

    public BeneficiaryDaoHibernate() {
        super(Beneficiary.class);
    }

    /**
     * <p>
     *     Get the Beneficiary detail from BeneficiaryManagerImpl and add Beneficiary detail to database. 
     * </p>
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application..
     */
    public void insertBeneficiary(Beneficiary beneficiary) throws DataBaseException {
        try {
            getSession().save(beneficiary);
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE INSERT VALID IFSC..");
        }
    }

    /**
     * <p>
     *     Retrieves all Beneficiary from database.
     *     Return all Beneficiary in List type.
     * </p>
     * 
     * @return list
     *     return the list of Beneficiaries.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public List < Beneficiary > retrieveAllBeneficiaries() throws DataBaseException {
        try {
            return getSession().createQuery("FROM Beneficiary").list();
        } catch (HibernateException e) {
            throw new DataBaseException("ACCOUNTS IS NOT AVAILABLE.");
        }
    }

    /**
     * <p>
     *     Update the status to Success in Beneficiary using database.
     * </p>
     * 
     * @param beneficiaryId
     *     beneficiaryId of Beneficiary.
     *      
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
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

    /**
     * <p>
     *     Update the status to Failure in Beneficiary using database.
     * </p>
     * 
     * @param beneficiaryId
     *     beneficiaryId of Beneficiary.
     *      
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
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
