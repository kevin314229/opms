package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.Account;
import com.opms.mapper.AccountMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.AccountService;

@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public PageView query(PageView pageView, Account account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", account);
		List<Account> list = accountMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public PageView queryNoMatch(Account account, PageView pageView) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", account);
		List<Account> list = accountMapper.queryNoMatch(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<Account> queryAll(Account t) {
		// TODO Auto-generated method stub
		return accountMapper.queryAll(t);
	}

	@Override
	public void delete(String id) throws Exception {
		accountMapper.delete(id);
	}

	@Override
	public void update(Account t) throws Exception {
		accountMapper.update(t);
	}

	@Override
	public Account getById(String id) {
		return accountMapper.getById(id);
	}

	@Override
	public void add(Account t) throws Exception {
		accountMapper.add(t);
	}

	@Override
	public Account querySingleAccount(String accountName) {
		return accountMapper.querySingleAccount(accountName);
	}

	@Override
	public Account isExist(String accountName) {
		return accountMapper.isExist(accountName);
	}

	@Override
	public Account countAccount(Account account) {
		return accountMapper.countAccount(account);
	}
}
