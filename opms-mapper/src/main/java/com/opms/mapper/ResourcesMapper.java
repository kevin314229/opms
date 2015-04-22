package com.opms.mapper;

import java.util.List;

import com.opms.base.BaseMapper;
import com.opms.entity.Resources;
import com.opms.entity.ResourcesRole;

public interface ResourcesMapper extends BaseMapper<Resources> {

	void updateSortOrder(Resources resources);

	public Resources isExist(String name);

	public int getMaxLevel();

	// <!-- 根据账号Id获取该用户的权限-->
	public List<Resources> findAccountResourcess(String accountId);

	/**
	 * @param roleId
	 * @return
	 */
	public List<Resources> findRoleRes(String roleId);

	public List<Resources> queryByParentId(Resources resources);

	/**
	 * 更新菜单排序号
	 * 
	 * @param ResourcesRole
	 */
	public void addRoleRes(ResourcesRole rr);

	public void deleteResourcesRole(String roleId);
}
