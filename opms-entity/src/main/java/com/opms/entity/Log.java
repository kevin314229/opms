package com.opms.entity;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.opms.util.JsonDateSerializer;

@SuppressWarnings("serial")
public class Log implements java.io.Serializable {
	private int id;
	private String username;
	private String module;
	private String action;
	private String actionTime;
	private String userIP;
	private Date operTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	/**
	 * 时间格式化
	 * 
	 * @return
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionTime() {
		return actionTime;
	}

	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

}
