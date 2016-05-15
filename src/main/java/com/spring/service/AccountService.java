package com.spring.service;

import com.spring.model.Account;

/**
 * Created by hzzhaolong on 2016/3/14.
 */
public interface AccountService {

    public Account getAccountByUserName(String userName);

    public boolean updateAccount(Account account);

    public boolean insertAccount(Account account);

}
