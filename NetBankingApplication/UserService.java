package com.netbanking.service;

import com.netbanking.dao.UserDao;
import com.netbanking.exception.UserException;
import com.netbanking.model.User;

public class UserService {
    UserDao userDao = new UserDao();
    
    public void insertUser(User user) throws UserException {
    	userDao.insertUser(user);
    	System.out.println("service");
    }
}
