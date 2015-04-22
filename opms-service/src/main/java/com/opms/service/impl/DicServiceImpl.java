package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.Dic;
import com.opms.mapper.DicMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.DicService;

@Transactional
@Service("dicService")
public class DicServiceImpl implements DicService {
	@Autowired
	private DicMapper dicMapper;

	@Override
	public PageView query(PageView pageView, Dic dic) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", dic);
		List<Dic> list = dicMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public void add(Dic dic) throws Exception {
		dicMapper.add(dic);
	}

	@Override
	public void delete(String id) throws Exception {
		dicMapper.delete(id);
	}

	@Override
	public Dic getById(String id) {
		return dicMapper.getById(id);
	}

	@Override
	public void update(Dic dic) throws Exception {
		dicMapper.update(dic);
	}

	@Override
	public List<Dic> queryAll(Dic dic) {
		return dicMapper.queryAll(dic);
	}

	@Override
	public Dic isExist(Dic dic) {
		return dicMapper.isExist(dic);
	}

}
