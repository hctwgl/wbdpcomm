package com.wbdp.start;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wbdp.server.WiseDataServer;
import com.wbdp.server.WiseDataServerHandler;
import com.wbdp.util.ThreadUtil;


/**
 *netty服务器启动类
 * @author 汪赛军
 *
 */
@WebServlet(description = "StartSocket", urlPatterns = { "/StartSocket" })
public class StartServer extends HttpServlet {
	private static Logger logger = Logger.getLogger(StartServer.class);
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StartServer() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			ThreadUtil t = new ThreadUtil();
			t.start();
			int port = 6666;
			WiseDataServer timeserver = new WiseDataServer();
			timeserver.bind(port);
			System.out.println("启动完成。。。。");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
