package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.Resource;
import com.opms.entity.ResourceRole;
import com.opms.mapper.ResourceMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.ResourceService;

@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public PageView query(PageView pageView, Resource resource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", resource);
		List<Resource> list = resourceMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<Resource> queryAll(Resource t) {
		return resourceMapper.queryAll(t);
	}

	@Override
	public void delete(String id) throws Exception {
		this.resourceMapper.delete(id);
	}

	@Override
	public void update(Resource t) throws Exception {
		this.resourceMapper.update(t);
	}

	@Override
	public Resource getById(String id) {
		return this.resourceMapper.getById(id);
	}

	@Override
	public List<Resource> queryByParentId(Resource resource) {
		return resourceMapper.queryByParentId(resource);
	}

	@Override
	public void add(Resource t) throws Exception {
		this.resourceMapper.add(t);
	}

	@Override
	public void updateSortOrder(List<Resource> resources) {
		for (Resource m : resources) {
			resourceMapper.updateSortOrder(m);
		}
	}

	@Override
	public List<Resource> findAccountResources(String accountId) {
		return resourceMapper.findAccountResources(accountId);
	}

	@Override
	public List<Resource> findRoleRes(String roleId) {
		return resourceMapper.findRoleRes(roleId);
	}

	@Override
	public void addRoleRes(String roleId, List<String> list) {
		resourceMapper.deleteResourceRole(roleId);
		for (String string : list) {
			ResourceRole rr = new ResourceRole();
			rr.setRoleId(roleId);
			rr.setResId(string);
			resourceMapper.addRoleRes(rr);
		}
	}

	@Override
	public int getMaxLevel() {
		return resourceMapper.getMaxLevel();
	}

	@Override
	public Resource isExist(String resourceName) {
		return resourceMapper.isExist(resourceName);
	}

}
