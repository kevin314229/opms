package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.DicType;
import com.opms.mapper.DicTypeMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.DicTypeService;

@Transactional
@Service("dicTypeService")
public class DicTypeServiceImpl implements DicTypeService {
	@Autowired
	private DicTypeMapper dicTypeMapper;

	@Override
	public PageView query(PageView pageView, DicType dicType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", dicType);
		List<DicType> list = dicTypeMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public void add(DicType dicType) throws Exception {
		dicTypeMapper.add(dicType);
	}

	@Override
	public void delete(String id) throws Exception {
		dicTypeMapper.delete(id);
	}

	@Override
	public DicType getById(String id) {
		return dicTypeMapper.getById(id);
	}

	@Override
	public void update(DicType dicType) throws Exception {
		dicTypeMapper.update(dicType);
	}

	@Override
	public List<DicType> queryAll(DicType dicType) {
		return dicTypeMapper.queryAll(dicType);
	}

	@Override
	public DicType queryById(DicType dicType) {
		return dicTypeMapper.queryById(dicType);
	}

	@Override
	public DicType isExist(DicType dicType) {
		return dicTypeMapper.isExist(dicType);
	}

}
