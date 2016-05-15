package com.spring.model;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author hzwangyuantao
 * @since 2015/10/20
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -7944897338817739900L;
	
	public static final int ACCOUNT_TYPE_URS = 1; //urs账号

	private Long userId; // user id

	private String userName; // urs账号就存urs邮箱，其他情况存第三方的id

	private Integer type;

	private Long createTime;

	private Long updateTime;

	/** 昵称 */
	private String nickname;

	/** app设备号 */
	private String deviceId;

	/** 用户指纹 */
	private String fingerprint;

	/** 用户第一次使用的ip */
	private String firstIp;

	/** 用户绑定的手机号,默认设置空串可以用索引 */
	private String phoneNum;
	
	/** 用户绑定手机号的时间 */
	private Long phoneNumBindTime;

	/** 是否已访问兴趣标签页 */
	private Integer isVisitInterest;

	/** 最近一次访问兴趣标签页的时间 */
	private Long lastVisitInterestTime;

	/** 头像链接 */
	private String headImgUrl;

	/** 人拉人邀请的老用户账号 */
	private String inviteAccountIdNew;

	/** 人拉人邀请的老用户优惠券派发次数 */
	private Integer invitePrizeTimesNew;

	/** 用户是否存在已支付订单；1-是，0－否 */
	private Integer hasPaidOrder;
	
	/** 用户等级：0表示普通，1表示vip */
	private Integer level; 

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getFirstIp() {
		return firstIp;
	}

	public void setFirstIp(String firstIp) {
		this.firstIp = firstIp;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getIsVisitInterest() {
		return isVisitInterest;
	}

	public void setIsVisitInterest(Integer isVisitInterest) {
		this.isVisitInterest = isVisitInterest;
	}

	public Long getLastVisitInterestTime() {
		return lastVisitInterestTime;
	}

	public void setLastVisitInterestTime(Long lastVisitInterestTime) {
		this.lastVisitInterestTime = lastVisitInterestTime;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getInviteAccountIdNew() {
		return inviteAccountIdNew;
	}

	public void setInviteAccountIdNew(String inviteAccountIdNew) {
		this.inviteAccountIdNew = inviteAccountIdNew;
	}

	public Integer getInvitePrizeTimesNew() {
		return invitePrizeTimesNew;
	}

	public void setInvitePrizeTimesNew(Integer invitePrizeTimesNew) {
		this.invitePrizeTimesNew = invitePrizeTimesNew;
	}

	public Integer getHasPaidOrder() {
		return hasPaidOrder;
	}

	public void setHasPaidOrder(Integer hasPaidOrder) {
		this.hasPaidOrder = hasPaidOrder;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getPhoneNumBindTime() {
		return phoneNumBindTime;
	}

	public void setPhoneNumBindTime(Long phoneNumBindTime) {
		this.phoneNumBindTime = phoneNumBindTime;
	}

}
