package com.opms.service;

import java.util.List;

import com.opms.base.BaseService;
import com.opms.entity.Operator;
import com.opms.entity.ServerInfo;
import com.opms.pulgin.mybatis.plugin.PageView;

public interface ServerInfoService extends BaseService<ServerInfo> {
	/**
	 * 返回分页后的运营商数据
	 * 
	 * @param pageView
	 * @param t
	 * @return
	 */
	public PageView queryOperator(PageView pageView, Operator operator);
	
	public List<Operator> queryAllOperators();
	
	public void add(Operator operator) throws Exception;

	public Operator getOperatorById(String operatorId);

	public void updateOperator(Operator operator) throws Exception;

	public void deleteOperator(String operatorId) throws Exception;	
	
}
