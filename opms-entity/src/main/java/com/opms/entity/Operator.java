package com.opms.entity;

import java.util.List;

/**
 * 运营商信息
 * 
 * @author kevin
 * 
 */
public class Operator {

	private int id;// 运营商ID

	private String name;// 运营商名称

	private String area;// 国家或地区
	
	private List<ServerInfo> serverInfos;//服务器

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<ServerInfo> getServerInfos() {
		return serverInfos;
	}

	public void setServerInfos(List<ServerInfo> serverInfos) {
		this.serverInfos = serverInfos;
	}

}
