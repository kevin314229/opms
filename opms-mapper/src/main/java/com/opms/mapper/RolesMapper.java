package com.opms.mapper;

import com.opms.base.BaseMapper;
import com.opms.entity.RoleAccount;
import com.opms.entity.Roles;

public interface RolesMapper extends BaseMapper<Roles> {
	public Roles isExist(String name);

	public Roles findbyAccountRole(String accountId);

	public void addAccRole(RoleAccount roleAccount);

	public void deleteAccountRole(String accountId);
}
