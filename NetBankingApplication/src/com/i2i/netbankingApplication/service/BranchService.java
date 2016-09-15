package com.i2i.netbankingApplication.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.netbankingApplication.constantVariableUtil.ConstantVariableUtil;
import com.i2i.netbankingApplication.dao.BranchDao;
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
 *
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
     *     emailId of branch enter the user add to new Branch.
     * 
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
    public void getBranch(String emailId) throws DataBaseException {
    	branchDao.addBranch(new Branch("I2I0BK" + String.valueOf(getLastIFSCode() + 1), emailId));
    }
    
    /**
     * <p>
     *     This method Calculate the last ifsCode. 
     *     return to the last ifSCode.
     * </p>
     * 
     * @return lastIFSC
     *     return to the Last ifsc code.
     *     
     * @throws DataBaseException
     *     If there is an error in the given data like BadElementException.
     */
    public int getLastIFSCode() throws DataBaseException, NumberFormatException {
    	int lastIFSC = ConstantVariableUtil.initializeVariable;
    	for (Branch branch : branchDao.retriveAllBranch()) {
    		String IFSC = branch.getIFSCode();
   			int temp = Integer.parseInt(IFSC.substring(6, IFSC.length()));
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
     *     If there is an error in the given data like BadElementException.
     */
	public void deleteBranchById(String IFSCode) throws DataBaseException {
		branchDao.deleteBranchById(IFSCode);
	}
	
	/**
	 * Retrieves all branches from retriveAllBranch in branchDao.
	 * 
	 * @return List
	 *     Return list of branches.
	 *      
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public List<Branch> getAllBranch() throws DataBaseException {
    	return branchDao.retriveAllBranch();
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
	 * @return Object
	 *     return the branch Object.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
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
     *     If there is an error in the given data like BadElementException.
     */
	public String getAddress(Address address) throws DataBaseException {
	    return branchDao.addAddress("I2I0BK" + String.valueOf(getLastIFSCode()), new Address(customerService.getNewAddressId(), address.getStreet(),
	        address.getCountry(), address.getCity(), address.getState() ,address.getPincode()));
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
	 *     If there is an error in the given data like BadElementException.
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
	 *     
	 * @param balance
	 *     balance of Account.
	 *     
	 * @param accounttype
	 *     accountType of Account.
	 *     
	 * @param ifsc
	 *     ifsc of Account.
	 *     
	 * @throws DataBaseException
	 *      If there is an error in the given data like BadElementException.
	 */
	public String getAccount(String accountNumber, double balance, String accounttype, String ifsc) throws DataBaseException {
		Branch branch = branchDao.retrieveBranchById(ifsc);
		//verify the branch id exist or not.
		if (branch != null) {
			return branchDao.addAccount(new Account(accountNumber, balance, accounttype, branch));
	    } else {
	     	return ("PLEASE ENTER VALID IFSC NUMBER"); 
		}
	}
	
	/**
	 * <p> 
	 *     This method use to view account by branch.
     *     This method get the branch IFSC from branch controller. 
     * </p>
     * 
	 * @param ifsc
	 *     ifsc of Account.
	 *     
	 * @return List.
	 *     return the list of accounts.
	 *     
	 * @throws DataBaseException
	 *     If there is an error in the given data like BadElementException.
	 */
	public List<Account> viewAccountByBranch(String ifsc) throws DataBaseException {
		List<Account> accounts = new ArrayList<Account>();
		if (branchDao.retrieveBranchById(ifsc) != null) {
			for (Account account : branchDao.retriveAllAccount()) {
				if (account.getBranch().getIFSCode().equals(ifsc)) {
					accounts.add(account);
				}
			}
			return accounts;
		} else {
			throw new DataBaseException("PLEASE ENTER VALID IFSC NUMBER"); 
		}
	}
}

