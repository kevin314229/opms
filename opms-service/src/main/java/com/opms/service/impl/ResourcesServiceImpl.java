package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.Resources;
import com.opms.entity.ResourcesRole;
import com.opms.mapper.ResourcesMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.ResourcesService;

@Transactional
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesMapper resourcesMapper;

	@Override
	public PageView query(PageView pageView, Resources resources) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", resources);
		List<Resources> list = resourcesMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<Resources> queryAll(Resources t) {
		return resourcesMapper.queryAll(t);
	}

	@Override
	public void delete(String id) throws Exception {
		this.resourcesMapper.delete(id);
	}

	@Override
	public void update(Resources t) throws Exception {
		this.resourcesMapper.update(t);
	}

	@Override
	public Resources getById(String id) {
		return this.resourcesMapper.getById(id);
	}

	@Override
	public List<Resources> queryByParentId(Resources resources) {
		return resourcesMapper.queryByParentId(resources);
	}

	@Override
	public void add(Resources t) throws Exception {
		this.resourcesMapper.add(t);
	}

	@Override
	public void updateSortOrder(List<Resources> resourcess) {
		for (Resources m : resourcess) {
			resourcesMapper.updateSortOrder(m);
		}
	}

	@Override
	public List<Resources> findAccountResourcess(String accountId) {
		return resourcesMapper.findAccountResourcess(accountId);
	}

	@Override
	public List<Resources> findRoleRes(String roleId) {
		return resourcesMapper.findRoleRes(roleId);
	}

	@Override
	public void addRoleRes(String roleId, List<String> list) {
		resourcesMapper.deleteResourcesRole(roleId);
		for (String string : list) {
			ResourcesRole rr = new ResourcesRole();
			rr.setRoleId(roleId);
			rr.setResId(string);
			resourcesMapper.addRoleRes(rr);
		}
	}

	@Override
	public int getMaxLevel() {
		return resourcesMapper.getMaxLevel();
	}

	@Override
	public Resources isExist(String resourcesName) {
		return resourcesMapper.isExist(resourcesName);
	}

}
