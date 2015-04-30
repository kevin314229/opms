package com.opms.service;

import java.util.List;

import com.opms.base.BaseService;
import com.opms.entity.Resource;

public interface ResourceService extends BaseService<Resource> {
	// <!-- 根据账号Id获取该用户的权限-->
	public List<Resource> findAccountResources(String accountId);

	/**
	 * @return
	 */
	public List<Resource> findRoleRes(String roleId);

	public List<Resource> queryByParentId(Resource resource);

	/**
	 * 更新菜单排序号
	 * 
	 * @param List<Resource>
	 */
	void updateSortOrder(List<Resource> menus);

	public void addRoleRes(String roleId, List<String> list);

	public Resource isExist(String menuName);

	public int getMaxLevel();
}
