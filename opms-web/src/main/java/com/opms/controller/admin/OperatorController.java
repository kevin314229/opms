package com.opms.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opms.controller.BaseController;
import com.opms.entity.Operator;
import com.opms.entity.Resource;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.ServerInfoService;
import com.opms.util.Common;
/**
 * 运营商
 * @author kevin
 *
 */
@Controller
@RequestMapping("/background/operator/")
public class OperatorController extends BaseController {

	@Autowired
	private ServerInfoService serverInfoService;
	/**
	 * 运营商列表
	 * @param model
	 * @param menu
	 * @param pageNow
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, Resource menu, String pageNow) {
		return Common.BACKGROUND_PATH + "/operator/list";
	}

	/**
	 * 查询所有运营商分页显示
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public PageView query(Operator operator, Integer pageNow, Integer pagesize) {
		pageView = serverInfoService.queryOperator(getPageView(pageNow, pagesize), operator);
		return pageView;
	}
	/**
	 * 查询所有运营商
	 * @return
	 */
	@ResponseBody
	@RequestMapping("queryAll")
	public List<Operator> queryAll(Operator operator) {
		return serverInfoService.queryAllOperators();
	}

	/**
	 * 跑到新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI() {
		return Common.BACKGROUND_PATH + "/operator/add";
	}

	/**
	 * 保存数据
	 * 
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(Operator operator) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			serverInfoService.add(operator);
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 跑到编辑界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model, Integer operatorId) {
		Operator operator = serverInfoService.getOperatorById(operatorId.toString());
		model.addAttribute("operator", operator);
		return Common.BACKGROUND_PATH + "/operator/edit";
	}

	/**
	 * 更新类型
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(Model model, Operator operator) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			serverInfoService.updateOperator(operator);
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param videoTypeId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public Map<String, Object> deleteById(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for (String string : id) {
				if (!Common.isEmpty(string)) {
					serverInfoService.deleteOperator(string);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}
	/**
	 * 选择运营商UI
	 */
	@RequestMapping("chooseUI")
	public String chooseUI(Model model){
		model.addAttribute("operators", serverInfoService.queryAllOperators());
		return Common.BACKGROUND_PATH + "/operator/chooseUI";
	}
	
}
