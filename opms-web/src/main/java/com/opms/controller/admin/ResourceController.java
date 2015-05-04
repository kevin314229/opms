package com.opms.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opms.controller.BaseController;
import com.opms.entity.Dic;
import com.opms.entity.Params;
import com.opms.entity.Resource;
import com.opms.pulgin.mybatis.plugin.PageView;
import com.opms.service.DicService;
import com.opms.service.ResourceService;
import com.opms.util.BeanUtil;
import com.opms.util.Common;
import com.opms.util.PropertiesUtils;
import com.opms.util.TreeObject;
import com.opms.util.TreeUtil;

/**
 * 菜单资源中心
 * @version 1.0v
 */
@Controller
@RequestMapping("/background/resource/")
public class ResourceController extends BaseController {
	@Inject
	private ResourceService resourceService;
	@Inject
	private DicService dicService;

	@ResponseBody
	@RequestMapping("perm")
	public Map<String, Object> perm(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * List<Department> dp = DepartmentUtil.getChildDeparts(departmentService.queryAll(new Department()), 0); map.put("records", dp);
		 */
		return map;
	}

	@RequestMapping("aution")
	public String aution(Model model) throws Exception {
		List<Resource> rs = resourceService.queryAll(new Resource());
		List<TreeObject> treeObjects = new ArrayList<TreeObject>();
		for (Resource res : rs) {// 转换为树对象
			TreeObject t = new TreeObject();
			PropertyUtils.copyProperties(t, res);
			treeObjects.add(t);
		}
		List<TreeObject> ns = TreeUtil.getChildResources(treeObjects, 0);
		model.addAttribute("permissions", ns);
		return Common.BACKGROUND_PATH + "/resource/permissions";
	}

	@ResponseBody
	@RequestMapping("findRoleRes")
	public List<Resource> findRoleRes(String roleId) {
		return resourceService.findRoleRes(roleId);
	}

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, Resource resource, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = resourceService.query(pageView, resource);
		model.addAttribute("pageView", pageView);
		return Common.BACKGROUND_PATH + "/resource/list";
	}

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model, Resource resource, String pageNow) {
		return Common.BACKGROUND_PATH + "/resource/list";
	}

	@ResponseBody
	@RequestMapping("resources")
	public Map<String, Object> resources(Resource resource, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Resource> rs;
		if (PropertiesUtils.findPropertiesKey("rootName").equals(Common.findAuthenticatedUsername())) {
			rs = resourceService.queryAll(resource);
		} else {
			rs = resourceService.findAccountResources(Common.findUserSessionId(request));
		}
		List<TreeObject> treeObjects = new ArrayList<TreeObject>();
		for (Resource res : rs) {// 转换为树对象
			TreeObject t = new TreeObject();
			PropertyUtils.copyProperties(t, res);
			treeObjects.add(t);
		}
		List<TreeObject> ns = TreeUtil.getChildResources(treeObjects, 0);
		BeanUtil.sort(ns, "id", false);
		map.put("resourceTree", ns);
		return map;
	}

	@ResponseBody
	@RequestMapping("queryAll")
	public List<Resource> queryAll(HttpServletRequest request) {
		if (PropertiesUtils.findPropertiesKey("rootName").equals(Common.findAuthenticatedUsername())) {// 根据账号拥有所有权限
			return resourceService.queryAll(new Resource());
		} else {
			return resourceService.queryAll(new Resource());
		}
	}

	/**
	 * 跳转到修改界面
	 * 
	 * @param model
	 * @param resourceId
	 *            修改菜单信息ID
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model, String resourceId) {
		List<Resource> resources = resourceService.queryAll(new Resource());
		model.addAttribute("resources", resources);
		Resource resource = resourceService.getById(resourceId);
		model.addAttribute("cur", resource);
		Dic dic = new Dic();
		dic.setDicTypeKey("res_type");
		model.addAttribute("dics", dicService.queryAll(dic));
		return Common.BACKGROUND_PATH + "/resource/edit";
	}

	/**
	 * 跳转到新增界面
	 * 
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		List<Resource> resources = resourceService.queryAll(new Resource());
		model.addAttribute("resources", resources);
		Dic dic = new Dic();
		dic.setDicTypeKey("res_type");
		model.addAttribute("dics", dicService.queryAll(dic));
		return Common.BACKGROUND_PATH + "/resource/add";
	}

	/**
	 * 权限分配页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("permissions")
	public String permissions(Model model) {
		return Common.BACKGROUND_PATH + "/resource/permissions";
	}

	/**
	 * 添加菜单
	 * 
	 * @param resource
	 * @return Map
	 */
	@RequestMapping("add")
	@ResponseBody
	public Map<String, Object> add(Resource resource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 判断是否为目录(目录的parentId为0)
			if (-1 == resource.getParentId()) {
				resource.setParentId(0);
			}
			resourceService.add(resource);
			map.put("flag", "true");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 更新菜单
	 * 
	 * @param model
	 * @param Map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("update")
	public Map<String, Object> update(Model model, Resource resource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			resourceService.update(resource);
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 根据ID删除菜单
	 * 
	 * @param model
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteById")
	public Map<String, Object> deleteById(Model model, String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id[] = ids.split(",");
			for (int i = 0, inv = id.length; i < inv; i++) {
				if (!Common.isEmpty(id[i])) {
					resourceService.delete(id[i]);
				}
			}
			map.put("flag", "true");
		} catch (Exception e) {
			map.put("flag", "false");
		}
		return map;
	}

	/**
	 * 验证菜单是否存在
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name) {
		Resource resource = resourceService.isExist(name);
		if (resource == null) {
			return true;
		} else {
			return false;
		}
	}

	@ResponseBody
	@RequestMapping("addRoleRes")
	public Map<String, Object> addRoleRes(String roleId, Params params) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = params.getId();
		try {
			if (null != list && list.size() > 0) {
				resourceService.addRoleRes(roleId, list);
				map.put("flag", "true");
			} else {
				map.put("flag", "false");
			}
		} catch (Exception e) {
			map.put("flag", "false");
		}

		return map;
	}

	@RequestMapping("sortUpdate")
	@ResponseBody
	public Map<String, Object> sortUpdate(Params params, HttpServletRequest request) throws Exception {
		resourceService.updateSortOrder(params.getResources());
		return resources(new Resource(), request);
	}
}