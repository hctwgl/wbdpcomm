package com.wisemifi.wx.model;

/**
 * 微信事件消息封装类，例如用户关注了公众号，则会发送该类消息到微信后台
 * @author 汪赛军
 * date:2017年8月9日下午3:01:21
 *
 */
public class BaseEvent {
	//开发者微信号
	private String ToUserName;
	//发送方微信号，openID
	private String FromUserName;
	//消息创建时间
	private Long CreateTime;
	//消息类型，event
	private String MsgType;
	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	private String Event;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	
	
}
