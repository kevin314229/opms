package com.opms.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opms.controller.BaseController;
import com.opms.entity.Log;
import com.opms.entity.Resource;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.LogService;
import com.opms.util.Common;

/**
 * 
 * @version 1.0v
 */
@Controller
@RequestMapping("/background/log/")
public class LogController extends BaseController {
	@Inject
	private LogService logService;

	/**
	 * 查询客户登陆信息
	 * 
	 * @param model
	 * @param log
	 * @param pageNow
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public PageView queryUserLogin(Log log, Integer pageNow, Integer pagesize) {
		pageView = logService.query(getPageView(pageNow, pagesize), log);
		return pageView;
	}

	@RequestMapping("list")
	public String list(Model model, Resource menu, String pageNow) {
		return Common.BACKGROUND_PATH + "/log/list";
	}
}
