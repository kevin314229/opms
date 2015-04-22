package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.RoleAccount;
import com.opms.entity.Roles;
import com.opms.mapper.RolesMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.RolesService;
import com.opms.util.Common;

@Transactional
@Service("RolesService")
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesMapper roleMapper;

	@Override
	public PageView query(PageView pageView, Roles roles) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", roles);
		List<Roles> list = roleMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<Roles> queryAll(Roles t) {
		return roleMapper.queryAll(t);
	}

	@Override
	public void delete(String id) throws Exception {
		roleMapper.delete(id);

	}

	@Override
	public void update(Roles t) throws Exception {
		roleMapper.update(t);

	}

	@Override
	public Roles getById(String id) {
		return roleMapper.getById(id);
	}

	@Override
	public void add(Roles t) throws Exception {
		roleMapper.add(t);
	}

	@Override
	public Roles isExist(String name) {
		return roleMapper.isExist(name);
	}

	@Override
	public Roles findbyAccountRole(String accountId) {
		return roleMapper.findbyAccountRole(accountId);
	}

	@Override
	public void addAccRole(RoleAccount roleAccount) {
		roleMapper.deleteAccountRole(roleAccount.getAccountId().toString());
		roleMapper.addAccRole(roleAccount);
	}

	@Override
	public void addAccRole(String accountId, List<String> ids) {
		roleMapper.deleteAccountRole(accountId);
		for (String roleId : ids) {
			if (!Common.isEmpty(roleId)) {
				RoleAccount roleAccount = new RoleAccount();
				roleAccount.setAccountId(Integer.parseInt(accountId));
				roleAccount.setRoleId(Integer.parseInt(roleId));
				roleMapper.addAccRole(roleAccount);
			}

		}
	}

}
