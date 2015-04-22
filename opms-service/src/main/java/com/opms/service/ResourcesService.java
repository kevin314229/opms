package com.opms.service;

import java.util.List;

import com.opms.base.BaseService;
import com.opms.entity.Resources;

public interface ResourcesService extends BaseService<Resources> {
	// <!-- 根据账号Id获取该用户的权限-->
	public List<Resources> findAccountResourcess(String accountId);

	/**
	 * @return
	 */
	public List<Resources> findRoleRes(String roleId);

	public List<Resources> queryByParentId(Resources resources);

	/**
	 * 更新菜单排序号
	 * 
	 * @param List<Resources>
	 */
	void updateSortOrder(List<Resources> menus);

	public void addRoleRes(String roleId, List<String> list);

	public Resources isExist(String menuName);

	public int getMaxLevel();
}
