package com.i2i.netBankingApplication.dao;

import java.util.List;

import com.i2i.netBankingApplication.exception.DataBaseException;
import com.i2i.netBankingApplication.model.Beneficiary;

public interface BeneficiaryDao extends GenericDao<Beneficiary, Long> {
    
    void insertBeneficiary(Beneficiary beneficiary) throws DataBaseException;
    
    List<Beneficiary> retrieveAllBeneficiaries() throws DataBaseException;

	void beneficiaryAccountActive(int beneficiaryId) throws DataBaseException;

	void beneficiaryAccountDeactive(int beneficiaryId) throws DataBaseException;

}
