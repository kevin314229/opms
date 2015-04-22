package com.opms.service;

import java.util.List;

import com.opms.base.BaseService;
import com.opms.entity.RoleAccount;
import com.opms.entity.Roles;

public interface RolesService extends BaseService<Roles> {
	public Roles isExist(String name);

	public Roles findbyAccountRole(String accountId);

	public void addAccRole(RoleAccount roleAccount);

	public void addAccRole(String accountId, List<String> ids);
}
