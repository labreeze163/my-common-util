package com.spring.dao;


import com.spring.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountDao {

	/**
	 * @param userName
	 *            登录账号
	 * @return
	 */
	Account getAccount(String userName);


	boolean updateAccountByUserName(Account account);

	boolean insertAccount(Account account);

}