package com.opms.controller.statistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opms.util.Common;
/**
 * 在线统计
 * @author kevin
 *
 */
@Controller
@RequestMapping("/online/")
public class OnlineController {

	@RequestMapping("now")
	public String selectCurrentOnline(){
		
		return Common.WEB_PATH + "/retention/currentOnline";
	}
}
