package com.i2i.netbankingapplication.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.i2i.netbankingapplication.dao.BranchDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.Branch;
import com.i2i.netbankingapplication.util.StringUtil;

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
     * Get the branch object from BranchService and add Branch to database. 
     * 
     * @param branch
     *     branch holds the Branch information.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public void insertBranch(Branch branch) throws DataBaseException {
        try {
            getSession().save(branch); 
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("addBranchError") + branch.getIFSCode());  
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
    public Branch retrieveBranchByIFSCode(String ifscode) throws DataBaseException {
        List branches = getSession().createCriteria(Branch.class).add(Restrictions.eq("IFSCode", ifscode)).list();
        if (branches == null || branches.isEmpty()) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("viewBranchError") + ifscode );
        } else {
            return (Branch) branches.get(0);
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
    public List<Branch> retrieveBranches() throws DataBaseException {
        try {
            return getSession().createQuery("FROM Branch").list();
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("viewAllBranchesError"));
        }
    }
    
    /**
     * Get the account object from BranchManager and add Branch database. 
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
        try {
            getSession().saveOrUpdate(account); 
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("insertAccount") + account.getAccountNumber() );  
        }
    }
    
     /**
     * <p>
     *     Get the accountNumber from BranchManager.
     *     Retrieves Account data from database and returns Account object to BranchService.
     * </p>
     * 
     * @param accountNumber
     *     accountNumber of Account to view.
     *     
     * @return branch
     *     Account class.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public Account retrieveAccountByAccountNumber(String accountNumber) throws DataBaseException {
        try {
            return (Account) getSession().get(Account.class, accountNumber);
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("retrieveAccountError"));
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
        try {
             return getSession().createQuery("FROM Account").list();
        } catch (HibernateException e) {
            throw new DataBaseException(StringUtil.informationReader().getProperty("viewAllAccountMessage"));
        }
    }
}
