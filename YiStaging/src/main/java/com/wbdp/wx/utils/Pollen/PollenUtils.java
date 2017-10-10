package com.wbdp.wx.utils.Pollen;

import net.sf.json.JSONObject;
import org.junit.Test;
import com.wbdp.wx.utils.http.HttpUtil;


public class PollenUtils {
	
	public static Object getPollen(long beeid){
		String result = HttpUtil.sendPost("http://www.wisedp.com/yfq/getPollen", "beeid="+beeid);
		System.out.println("result:"+result);
		JSONObject json = JSONObject.fromObject(result);
		Object object = json.get("data");
		return object;
	}
	
	@Test
	public void test(){
		Object pollen = getPollen(48);
		System.out.println("pollen:"+pollen);
	}
}
