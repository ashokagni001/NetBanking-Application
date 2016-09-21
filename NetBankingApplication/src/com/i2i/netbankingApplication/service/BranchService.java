package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.constant.Constant;
import com.i2i.netbankingApplication.dao.BranchDao;
import com.i2i.netbankingApplication.exception.BranchDataException;
import com.i2i.netbankingApplication.exception.CustomerDataException;
import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.model.Account;
import com.i2i.netbankingApplication.model.Address;
import com.i2i.netbankingApplication.model.Branch;

/**
 * <p>
 *     When request comes from branchController. branch service performs add or fetch or fetchAll branch with model(branch),
 *     DAO(branch) and return the responses to branchController.
 *     branchService operate passing value's to branchDao based on requset's from branchController.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03.
 */
public class BranchService {
	private BranchDao branchDao = new BranchDao();
	private CustomerService customerService = new CustomerService();
    
    /**
     * <p> 
     *     Get the emailId from branchController and pass to addBranch method in branchDao.
     *     calculate the last ifsCode in BranchDao.
     * </p>
     * 
     * @param emailId
     *     emailId of branch used to add a new Branch.
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
	
    public void addBranch(String emailId) throws DataBaseException {
    	branchDao.insertBranch(new Branch(Constant.IFSCODE + String.valueOf(getLastIFSCode() + 1), emailId));
    }
    
    /**
     * <p>
     *     This method Calculate the last ifsCode. 
     *     return to the last ifSCode.
     * </p>
     * 
     * @return lastIFSC
     *     return to the last value of Last IfsCode code.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
    public int getLastIFSCode() throws DataBaseException, NumberFormatException {
    	int lastIFSC = Constant.INITIALIZEVARAILABLEVALUE;
    	for (Branch branch : branchDao.retriveAllBranches()) {
    		String IFSC = branch.getIFSCode();
   			int temp = Integer.parseInt(IFSC.substring(Constant.IFSCODECONSTANTPREFIXVALUE, IFSC.length()));
   			if (lastIFSC <= temp) {
               	lastIFSC = temp;
            }
    	}
   	    return lastIFSC;
   	}

    /**
     * <p> 
     *     This method get the IFSCode from Branch controller and
     *     pass to IFSCode deleteBranchById method in branchDao.
     * </p>
     * 
     * @param IFSCode
     *     IFSCode of branch.It IfsCode used to delete corresponding records.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
	public String deleteBranchById(String IFSCode) throws DataBaseException {
		branchDao.deleteBranchById(IFSCode);
		return "YOUR BRANCH DELETED SUCCESSFULLY";
	}
	
	/**
	 * Retrieves all branches from retriveAllBranch in branchDao.
	 * 
	 * @return List
	 *     Return list of branches.
	 *      
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking application.
	 */
	public List<Branch> getAllBranches() throws DataBaseException {
    	return branchDao.retriveAllBranches();
	}
    
	/**
	 * <p> 
     *     Get the IFSCode from BranchController.
     *     It is passed to retrieveBranchById method in BranchDao and 
     *     returns branch object to BranchController.
     * </p>
     * 
	 * @param IFSCode
	 *     IFSCode of branch.It IfsCode used to view corresponding records.
	 *     
	 * @return branch
	 *     return the branch Object.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking application.
	 */
	public Branch getBranchById(String IFSCode) throws DataBaseException {
        return branchDao.retrieveBranchById(IFSCode); 
    }
	
    
	/**
     * <p>
     *     This method get the branch address from branch controller. 
     *     pass to branch address addAddress method in branchDao.
     * </p>
     * 
     * @param address
     *     Object of Address model class.It object used for add branch Address.
     *     
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking application.
     */
	public String addAddress(Address address) throws BranchDataException, DataBaseException {
		String IFSCode = Constant.IFSCODE + String.valueOf(getLastIFSCode());
		try {
		    branchDao.insertAddress(IFSCode, new Address(customerService.getNewAddressId(), address.getStreet(),
	                address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
		return ("BRANCH ADDED SUCCESSFULL. BRANCH IFSC CODE IS :" + IFSCode);
		} catch(DataBaseException e) {
			branchDao.deleteBranchById(IFSCode);
			throw new BranchDataException("SORRY DATA NOT INSERTED. TRY AGAIN");
		}
    }
    
	/**
	 * <p> 
     *     Get the address Id from branchController.
     *     It is passed to retrieveAddressById method in branchDao and 
     *     returns address object to branchController.
     * </p>
     * 
	 * @param addressId
	 *     Id of Address.
	 *     
	 * @return branchController
     *     Return to the object of Address class. 
     *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking application.
	 */
	public Address getAddressById(int addressId) throws DataBaseException {
	    return branchDao.retrieveAddressById(addressId);
	}
	
	/**
	 * <p>
	 *    This method verify the branch ifsc exist or not.
	 *    if branch exist add new account.
	 *    otherwise, return the error message.
	 * </p>
	 * 
	 * @param accountNumber
	 *     accountNumber of Account.
	 * @param balance
	 *     balance of Account.
	 * @param accounttype
	 *     accountType of Account.
	 * @param ifsc
	 *     IfsCode of Account.
	 *     
	 * @throws DataBaseException
	 *      It handle all the custom exception in NetBanking application.
	 */
	public String addAccount(String accountNumber, double balance, String accounttype, String ifsc) throws DataBaseException {
		Branch branch = branchDao.retrieveBranchById(ifsc);
		//verify the branch id exist or not.
		if (null != branch) {
			branchDao.insertAccount(new Account(accountNumber, balance, accounttype, branch));
			return ("ACCOUNT ADDED SUCCESSFULLY");
	    } else {
	     	return ("PLEASE ENTER VALID IFSC NUMBER"); 
		}
	}
	
	/**DataBaseException
	 * <p> 
	 *     This method use to view account by branch.
     *     This method get the branch IFSC from branch controller. 
     * </p>
     * 
	 * @param ifsc
	 *     IfsCode of Account.
	 *     
	 * @return List.
	 *     return the list of accounts.
	 *     
	 * @throws DataBaseException
	 *     It handle all the custom exception in NetBanking application.
	 * @throws BranchDataException 
	 *     It handle all the custom exception.
	 */
	public List<Account> viewAccountByBranch(String ifsc) throws DataBaseException, BranchDataException {
		List<Account> accounts = new ArrayList<Account>();
		if (null != branchDao.retrieveBranchById(ifsc)) {
			for (Account account : branchDao.retriveAllAccounts()) {
				if (account.getBranch().getIFSCode().equals(ifsc)) {
					accounts.add(account);
				}
			}
			return accounts;
		} else {
			throw new BranchDataException("PLEASE ENTER VALID IFSC NUMBER"); 
		}
	}
}

