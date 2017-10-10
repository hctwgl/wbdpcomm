package com.wisemifi.wx.service;

import com.wisemifi.wx.entity.WiseClient;

/**
 * 测试框架业务接口类
 * @author 汪赛军
 * date:2017年8月8日下午4:18:33
 *
 */
public interface ClientService {
	/**
	 * 测试框架整合
	 * @return
	 */
	public WiseClient selectWiseClient();
}
