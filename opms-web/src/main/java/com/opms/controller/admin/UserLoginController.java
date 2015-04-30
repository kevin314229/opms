package com.opms.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opms.controller.BaseController;
import com.opms.entity.Resource;
import com.opms.entity.UserLogin;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.UserLoginService;
import com.opms.util.Common;

/**
 * 
 * @version 1.0v
 */
@Controller
@RequestMapping("/background/userLogin/")
public class UserLoginController extends BaseController {
	@Inject
	private UserLoginService userLoginService;

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String list(Model model, Resource menu, String pageNow) {
		return Common.BACKGROUND_PATH + "/userLogin/list";
	}

	@ResponseBody
	@RequestMapping("queryList")
	public PageView query(UserLogin userLogin, Integer pageNow, Integer pagesize) {
		pageView = userLoginService.query(getPageView(pageNow, pagesize), userLogin);
		return pageView;
	}

}