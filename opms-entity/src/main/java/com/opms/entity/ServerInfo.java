package com.opms.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.opms.util.JsonDateSerializer;

/**
 * 服务器信息
 * 
 * @author kevin
 * 
 */
public class ServerInfo {

	private int id;// 服务器

	private String name;// 服务器名称

	private String ip;// 服务器访问IP

	private int port;// 访问端口

	private String visitKey;// 访问key

	private int operatorId;// 运营商ID

	private String cpuUsage;// cpu使用率

	private String setCpuUsage;// 预设cpu使用率

	private String jvmUsage;// 当前使用Jvm使用率

	private String setJvmUsage;// 预设Jvm使用率

	private String ramUsage;// 当前使用Ram使用率

	private String setRamUsage;// 预设Ram使用率

	private Date operTime;// 发送时间

	private String email;// 发送的邮件

	private String stringTime;// 时间字符串

	private String mark;// 扩展

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStringTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (operTime != null) {
			stringTime = sdf.format(operTime);
		}
		return stringTime;
	}

	public String getSetJvmUsage() {
		return setJvmUsage;
	}

	public void setSetJvmUsage(String setJvmUsage) {
		this.setJvmUsage = setJvmUsage;
	}

	public String getSetRamUsage() {
		return setRamUsage;
	}

	public void setSetRamUsage(String setRamUsage) {
		this.setRamUsage = setRamUsage;
	}

	public String getSetCpuUsage() {
		return setCpuUsage;
	}

	public void setSetCpuUsage(String setCpuUsage) {
		this.setCpuUsage = setCpuUsage;
	}

	public String getJvmUsage() {
		return jvmUsage;
	}

	public void setJvmUsage(String jvmUsage) {
		this.jvmUsage = jvmUsage;
	}

	public String getRamUsage() {
		return ramUsage;
	}

	public void setRamUsage(String ramUsage) {
		this.ramUsage = ramUsage;
	}

	public void setStringTime(String stringTime) {
		this.stringTime = stringTime;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getVisitKey() {
		return visitKey;
	}

	public void setVisitKey(String visitKey) {
		this.visitKey = visitKey;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}
	
}
