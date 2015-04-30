package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.Operator;
import com.opms.entity.ServerInfo;
import com.opms.mapper.OperatorMapper;
import com.opms.mapper.ServerInfoMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.ServerInfoService;

@Transactional
@Service("serverInfoService")
public class ServerInfoServiceImpl implements ServerInfoService {
	@Autowired
	private ServerInfoMapper serverInfoMapper;
	@Autowired
	private OperatorMapper operatorMapper;

	@Override
	public void add(ServerInfo serverInfo) throws Exception {
		serverInfoMapper.add(serverInfo);
	}

	@Override
	public void delete(String id) throws Exception {
		serverInfoMapper.delete(id);
	}

	@Override
	public ServerInfo getById(String id) {
		return serverInfoMapper.getById(id);
	}

	@Override
	public PageView query(PageView pageView, ServerInfo serverInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", serverInfo);
		List<ServerInfo> list = serverInfoMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<ServerInfo> queryAll(ServerInfo serverInfo) {
		return serverInfoMapper.queryAll(serverInfo);
	}

	@Override
	public void update(ServerInfo serverInfo) throws Exception {
		serverInfoMapper.update(serverInfo);
	}

	@Override
	public PageView queryOperator(PageView pageView, Operator operator) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		List<Operator> list = operatorMapper.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<Operator> queryAllOperators() {
		return operatorMapper.queryAll(null);
	}
	
	@Override
	public void add(Operator operator) throws Exception {
		operatorMapper.add(operator);
	}

	@Override
	public Operator getOperatorById(String operatorId) {
		return operatorMapper.getById(operatorId);
	}

	@Override
	public void updateOperator(Operator operator) throws Exception {
		operatorMapper.update(operator);
	}

	@Override
	public void deleteOperator(String operatorId) throws Exception {
		operatorMapper.delete(operatorId);
	}
}
