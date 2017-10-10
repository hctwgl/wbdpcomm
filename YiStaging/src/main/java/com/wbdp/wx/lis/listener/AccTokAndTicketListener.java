package com.wbdp.wx.lis.listener;

import com.wbdp.wx.runnable.AcctokenRunnable;
import com.wbdp.wx.utils.phone.SentVerifieCodeUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * spring的监听器 用于容器启动则开始进行监听
 * Created by wisedata005 on 2017/7/5.
 */
//@WebListener
public class AccTokAndTicketListener implements ServletContextListener {


    /**日志log*/
    private static Logger log = Logger.getLogger(SentVerifieCodeUtils.class);

    public void contextDestroyed(ServletContextEvent arg0) {

    }

    public void contextInitialized(ServletContextEvent arg0) {
        log.info("AccTokAndTicketListener监听启动，并与1.9小时获取一次access_token和jsAPI_tiket");
        contextInitialized();
    }

    private void contextInitialized() {
        Timer timer = new Timer();
        timer.schedule(new AcctokenRunnable(), 0, 1000 * 58 * 2*60);
    }

}
