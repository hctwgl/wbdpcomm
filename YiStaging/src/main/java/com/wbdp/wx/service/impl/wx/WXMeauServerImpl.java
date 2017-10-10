package com.wbdp.wx.service.impl.wx;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wbdp.wx.model.TextMessage;
import com.wbdp.wx.service.wx.WXMeauServer;
import com.wbdp.wx.utils.wx.MessageUtil;


@Service
public class WXMeauServerImpl implements WXMeauServer {
	private static Logger log = Logger.getLogger(WXMeauServerImpl.class);
	@Override
	public String MeauType(HttpServletRequest request) {
		// xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "暂不支持该类型消息";
        try {
        	// 调用parseXml方法解析请求消息
            Map requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = (String) requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = (String) requestMap.get("ToUserName");
            // 消息类型
            String msgType = (String) requestMap.get("MsgType");
            log.info("收到的消息类型："+msgType);
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            // 用户关注消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
            	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)
                String event = (String)requestMap.get("Event");
                log.info("用户关注标记："+event);
                if(event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                	respContent =   "尊敬的用户，您好！\r\n" +
                    		"欢迎您关注一分期！\r\n" +
                    		"分期专家，为用户提供更优质的分期体验。\r\n" +
                    		"一分期与您同行，让您的生活更精彩。";
                }
            }
            // 设置文本消息的内容
            textMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml(textMessage);
            return respXml;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
