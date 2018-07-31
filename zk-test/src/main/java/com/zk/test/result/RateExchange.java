package com.zk.test.result;

import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.READER;

public class RateExchange {
	
	public static Map<String,String> abbreviation(){
		Map<String,String> result= new HashMap<String, String>();
		result.put("CNY", "人民币");
		result.put("USD", "美元");
		result.put("HKD", "港币");
		result.put("KRW", "韩元");
		result.put("CAD", "加拿大元");
		result.put("EUR", "欧元");
		result.put("JPY", "日元");
		result.put("GBP", "英镑");
		return result;
	}

}
