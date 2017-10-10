package com.wbdp.tsp.service;

import com.wbdp.tsp.entity.qxkj.createQuoteB.CResult;
import com.wbdp.tsp.entity.qxkj.querycarmodelInfos.QResult;

public interface QxkjService {

	/*
	 * 1.查询车型
	 *  
	 *  vehicleName(车型,例:奥迪)
	 */
	public QResult queryCarModelInfos(String vehicleName);
	
	/*
	 * 2,创建报价
	 */
	public CResult createQuoteB();
	
	/*
	 * 3,提交报价
	 */
	public String submitQuote(String taskId);
	
	/*
	 * 4,报价回调
	 */
	public String querySubmitQuoteResult(String taskId);
}
