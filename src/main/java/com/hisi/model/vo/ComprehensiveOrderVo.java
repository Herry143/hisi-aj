package com.hisi.model.vo;

import java.util.Date;

public class ComprehensiveOrderVo {
	private int id;
	private String channelId;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnpackAccount() {
		return unpackAccount;
	}
	public void setUnpackAccount(String unpackAccount) {
		this.unpackAccount = unpackAccount;
	}
	private String orderId;

    private String flightId;

    private Integer num;

    private String carryPerson;
    
    private String checkPerson;
    private String proxyName;
    private String unpackAccount;
    
	public String getProxyName() {
		return proxyName;
	}
	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}
	private Date createTime;

    private Date startTime;

    private Date endTime;
    private Integer status;
    public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private String isUnpack;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getCarryPerson() {
		return carryPerson;
	}
	public void setCarryPerson(String carryPerson) {
		this.carryPerson = carryPerson;
	}
	public String getCheckPerson() {
		return checkPerson;
	}
	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getIsUnpack() {
		return isUnpack;
	}
	public void setIsUnpack(String isUnpack) {
		this.isUnpack = isUnpack;
	}
    
}
