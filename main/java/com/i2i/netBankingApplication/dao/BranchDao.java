package com.i2i.netBankingApplication.dao;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Account;
import com.i2i.netBankingApplication.model.Branch;

/**
 * <p>
 *     When request comes from BrancManager. BranchDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to BranchService.
 * </p>
 * 
 * @author ASHOK
 * 
 * @created 2016-09-27
 */
public interface BranchDao extends GenericDao<Branch, Long> {
    
    void insertBranch(Branch branch) throws DataBaseException ;
     
    void removeBranchByIFSCode(String IFSCode) throws DataBaseException ;
     
    Branch retrieveBranchByIFSCode(String IFSCode) throws DataBaseException ;
    
    List<Branch> retrieveBranches() throws DataBaseException ;
    
    void insertAccount(Account account) throws DataBaseException ;
    
    List<Account>retriveAllAccounts() throws DataBaseException ;
}
