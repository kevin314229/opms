package com.opms.controller.statistics;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opms.common.ApiConnection;
import com.opms.common.GameApi;
import com.opms.entity.ServerInfo;
import com.opms.service.ServerInfoService;
import com.opms.util.Common;
/**
 * 在线统计
 * @author kevin
 *
 */
@Controller
@RequestMapping("/online/")
public class OnlineController {

	@Autowired
	private ServerInfoService serverInfoService;
	
	/**
	 * 转向查看当前在线人数页面
	 * @return
	 */
	@RequestMapping("current")
	public String selectCurrentOnlineUI(){
		
		return Common.WEB_PATH + "/retention/currentOnline";
	}
	/**
	 * 当前实际在线人数
	 * @param serverId
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("now")
	public JSONObject nowOnline(Model model, @RequestParam("operator")Integer operatorId, @RequestParam("server")Integer serverId) throws Exception{
		JSONObject jsonData = new JSONObject();
		List<JSONObject> list = new ArrayList<JSONObject>();
		if (serverId <= 0) {//当前运营商下所有区服
			List<ServerInfo> serverInfos = null;
			ServerInfo serverInfo = new ServerInfo();
			if (operatorId > 0) {
				serverInfo.setOperatorId(operatorId);
			}
            serverInfos = serverInfoService.queryAll(serverInfo);
            for (ServerInfo server : serverInfos) {
            	JSONObject result = ApiConnection.request(server.getApiUrl(GameApi.CURRENT_ONLINE), ApiConnection.JSON, null);
				result.put("name", server.getName());
				list.add(result);
			}
		}else {
			ServerInfo serverInfo = serverInfoService.getById(serverId.toString());
			if (serverInfo != null) {
				JSONObject result = ApiConnection.request(serverInfo.getApiUrl(GameApi.CURRENT_ONLINE), ApiConnection.JSON, null);
				result.put("name", serverInfo.getName());
				list.add(result);
			}
		}
		jsonData.put("Rows", list);
		return jsonData;
	}
}
