package com.opms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opms.entity.UserLogin;
import com.opms.mapper.UserLoginMapper;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.UserLoginService;

@Transactional
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
	@Autowired
	private UserLoginMapper userLoginMap;

	@Override
	public PageView query(PageView pageView, UserLogin userLogin) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paging", pageView);
		map.put("t", userLogin);
		List<UserLogin> list = userLoginMap.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
	public List<UserLogin> queryAll(UserLogin t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(UserLogin t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public UserLogin getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(UserLogin t) throws Exception {
		userLoginMap.add(t);
		// TODO Auto-generated method stub

	}

}
