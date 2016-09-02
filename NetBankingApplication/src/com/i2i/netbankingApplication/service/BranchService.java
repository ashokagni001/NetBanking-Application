package com.i2i.netbankingApplication.service;

import com.i2i.netbankingApplication.dao.BranchDao;
import com.i2i.netbankingApplication.model.Branch;

public class BranchService {
    BranchDao branchDao = new BranchDao();
    public void getBranch(Branch branch) {
    	branchDao.addBranch(branch);
    }
}
