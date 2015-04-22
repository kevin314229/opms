package com.opms.service;

import com.opms.base.BaseService;
import com.opms.entity.Account;
import com.opms.pulgin.mybatis.plugin.PageView;

public interface AccountService extends BaseService<Account> {
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

	/**
	 * @param account
	 * @param pageView
	 * @return
	 */
	public PageView queryNoMatch(Account account, PageView pageView);
}
