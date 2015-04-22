package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.Log;
import com.opms.mapper.LogMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.LogService;

@Transactional
@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public PageView query(PageView pageView, Log log) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", log);
		List<Log> list = logMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public void add(Log log) throws Exception {
		logMapper.add(log);
	}

	@Override
	public void delete(String id) throws Exception {
		logMapper.delete(id);
	}

	@Override
	public Log getById(String id) {
		return logMapper.getById(id);
	}

	@Override
	public void update(Log log) throws Exception {
		logMapper.update(log);
	}

	@Override
	public List<Log> queryAll(Log log) {
		return logMapper.queryAll(log);
	}

}
