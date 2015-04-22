package com.opms.mapper;

import java.util.List;
import java.util.Map;

import com.opms.base.BaseMapper;
import com.opms.entity.Account;

public interface AccountMapper extends BaseMapper<Account> {
	public Account querySingleAccount(String accountName);

	public Account isExist(String accountName);

	/**
	 * 验证用户登陆
	 * 
	 * @param Account
	 *            account
	 * @return
	 */
	public Account countAccount(Account account);

	public List<Account> queryNoMatch(Map<String, Object> map);
}
