package com.wisemifi.wx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisemifi.wx.dao.WiseClientMapper;
import com.wisemifi.wx.entity.WiseClient;
import com.wisemifi.wx.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private WiseClientMapper wiseClientMapper;
	@Override
	public WiseClient selectWiseClient() {
		try {
			WiseClient client = wiseClientMapper.selectWiseClient();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
