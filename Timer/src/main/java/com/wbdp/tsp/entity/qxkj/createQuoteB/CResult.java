package com.wbdp.tsp.entity.qxkj.createQuoteB;

import java.io.Serializable;

public class CResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String taskId;   //报价任务taskId

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
