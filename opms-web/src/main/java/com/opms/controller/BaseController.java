package com.opms.controller;

import com.opms.pulgin.mybatis.plugin.PageView;

/**
 * 
 */
public class BaseController {

	public PageView pageView = null;

	public PageView getPageView(Integer pageNow, Integer pageSize) {
		if (pageNow == null) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(pageNow);
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		pageView.setPageSize(pageSize);
		return pageView;
	}
}