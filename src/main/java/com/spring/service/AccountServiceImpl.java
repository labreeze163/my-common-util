package com.spring.service;

import com.spring.dao.AccountDao;
import com.spring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hzzhaolong on 2016/3/14.
 */
@Service
public class AccountServiceImpl implements  AccountService{

    @Autowired
    private AccountDao accountDao;

    public Account getAccountByUserName(String userName) {
        Account account = accountDao.getAccount(userName);
        return account;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public boolean updateAccount(Account account) {
        boolean b = false;
        if(b) {
            insertAccount(account);
        }
        account.setUpdateTime(System.currentTimeMillis());
        return accountDao.updateAccountByUserName(account);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean insertAccount(Account account) {
        return accountDao.insertAccount(account);
    }


}
