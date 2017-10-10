package com.wisemifi.wx.model;

/**
 * 响应消息封装类，当用户关注公众号，微信后台则会推送该类消息到用户
 * @author 汪赛军
 * date:2017年8月9日下午3:05:44
 *
 */
public class BaseMessage {
	//接收方微信号，openID
	private String ToUserName;
	//开发者微信号
	private String FromUserName;
	//消息创建时间
	private Long CreateTime;
	//消息类型，text
	private String MsgType;
	//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	private String Content;
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
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	
}
