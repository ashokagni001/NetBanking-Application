package com.i2i.netBankingApplication.dao;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Beneficiary;

/**
 * Beneficiary Data Access Object (GenericDao) interface.
 * 
 * @author team2
 *
 */
public interface BeneficiaryDao extends GenericDao<Beneficiary, Long> {
    
    /**
     * Insert new beneficiary;
     *
     */
    void insertBeneficiary(Beneficiary beneficiary) throws DataBaseException;
    
    /**
     * Retrieve all beneficiaries;
     *
     *@return List
     */
    List<Beneficiary> retrieveAllBeneficiaries() throws DataBaseException;
    
    /**
     * Update active beneficiary;
     *
     */
    void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException;
    
    /**
     * Update deactive beneficiary;
     *
     */
    void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException;

}
