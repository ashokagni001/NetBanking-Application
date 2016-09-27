package com.i2i.netBankingApplication.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.i2i.netBankingApplication.dao.BranchDao;
import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Branch;


/**
 * <p>
 *     When request comes from BrancManager. BranchDaoHibernate performs add or delete or fetch or fetchAll 
 *     with database and return the responses to BrancManager.
 * </p>
 * 
 * @author TEAM2
 * 
 * @created 2016-09-27
 */
@Repository("branchDao")
public class BranchDaoHibernate extends GenericDaoHibernate<Branch, Long> implements BranchDao {

    /**
     * Constructor to create a Generics-based version using Branch as the entity
     */
    public BranchDaoHibernate() {
        super(Branch.class);
    }
     
    /**
     * {@inheritDoc}
     */
    public void insertBranch(Branch branch) throws DataBaseException {
        try {
            getSession().save(branch); 
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE INSERT VALID IFSC..");  
        }
    }
     
    /**
     * {@inheritDoc}
     * 
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    public Branch retrieveBranchByIFSCode(String IFSCode) throws DataBaseException {
        List branches = getSession().createCriteria(Branch.class).add(Restrictions.eq("IFSCode", IFSCode)).list();
        if (branches == null || branches.isEmpty()) {
            throw new DataBaseException("Branch " + IFSCode + " Not Found...");
        } else {
            return (Branch) branches.get(0);
        }
    }
     
    /**
     * {@inheritDoc}
     */
    public void removeBranchByIFSCode(String IFSCode) throws DataBaseException {
        Branch branch = retrieveBranchByIFSCode(IFSCode);
        Session session = getSessionFactory().getCurrentSession();
        session.delete(branch);
    }
     
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Branch> retrieveBranches() throws DataBaseException {
        Query qry = getSession().createQuery("from Branch u order by upper(u.IFSCode)");
        return qry.list();
    }
    
    /**
     * Get the account object from BranchService and add Branch database. 
     * 
     * @param account
     *     It Account datail use add new account.
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    @Override
    public void insertAccount(Account account) throws DataBaseException {
        try {
            getSession().save(account); 
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE CHECK YOUR DATAS " + account.getAccountNumber() + " YOUR DATA IS NOT VALID.PLEASE TRY AGAIN." );  
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
    public List<Account> retriveAllAccounts() throws DataBaseException {
        Query qry = getSession().createQuery("from Account u order by upper(u.accountNumber)");
        return qry.list();
    }

    
}
