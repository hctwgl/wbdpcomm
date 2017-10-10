package com.wisemifi.wx.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;


/**
 * spring的监听器 用于容器启动则开始进行监听
 * @author wisedata005
 */
public class AccTokAndTicketListener implements ServletContextListener {

	private Logger log = Logger.getLogger(AccTokAndTicketListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.info("AccTokAndTicketListener监听启动，并与1.9小时获取一次access_token和jsAPI_tiket");
		contextInitialized();
	}

	private void contextInitialized() {
		Timer timer = new Timer();
		timer.schedule(new AccesstokenRunnable(), 0, 1000 * 58 * 2*60);
	}
}
