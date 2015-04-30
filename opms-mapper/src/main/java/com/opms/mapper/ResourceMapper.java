package com.opms.mapper;

import java.util.List;

import com.opms.base.BaseMapper;
import com.opms.entity.Resource;
import com.opms.entity.ResourceRole;

public interface ResourceMapper extends BaseMapper<Resource> {

	void updateSortOrder(Resource resource);

	public Resource isExist(String name);

	public int getMaxLevel();

	// <!-- 根据账号Id获取该用户的权限-->
	public List<Resource> findAccountResources(String accountId);

	/**
	 * @param roleId
	 * @return
	 */
	public List<Resource> findRoleRes(String roleId);

	public List<Resource> queryByParentId(Resource resource);

	/**
	 * 更新菜单排序号
	 * 
	 * @param ResourceRole
	 */
	public void addRoleRes(ResourceRole rr);

	public void deleteResourceRole(String roleId);
}
