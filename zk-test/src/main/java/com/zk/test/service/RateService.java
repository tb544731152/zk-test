package com.zk.test.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zk.test.domain.Rate;
import com.zk.test.result.RateExchange;
import com.zk.test.util.HttpUitls;

@Service
public class RateService {
	private static Logger log = LoggerFactory.getLogger(RateService.class);
	private final String AppKey="2d3eb1cd19e764451d45505d01f7e7cc";
	
	/**
	 * 
	 * @param from 现有币种
	 * @param to   转换的币种
	 * @param amount 现有金额
	 * @return 
	 */
	public Rate exchange(String from,String to,BigDecimal amount) {
    	String fromName=RateExchange.abbreviation().get(from);
    	String toName=RateExchange.abbreviation().get(to);
    	Map<String,BigDecimal>  rateResult=getRate();
    	BigDecimal fromRate =rateResult.get(fromName);
    	BigDecimal toRate =rateResult.get(toName);
    	BigDecimal amount_res=fromRate.divide(toRate,2,RoundingMode.HALF_DOWN).multiply(amount);
    	BigDecimal rate = fromRate.divide(toRate,2,RoundingMode.HALF_DOWN);
		return new Rate(from, to,amount_res ,rate);
	}
	//获取汇率
	private Map<String,BigDecimal> getRate(){
		Map<String,BigDecimal> resulMap =new HashMap<String, BigDecimal>();
		String url="http://op.juhe.cn/onebox/exchange/query";
		String param="key="+AppKey;
		 try {
			 String result= HttpUitls.sendGet(url, param);
	         JSONObject object = JSONObject.fromObject(result);
	         if(object.getInt("error_code")==0){
	        	 	if(log.isInfoEnabled()){
	        	 		log.info(object.get("result").toString());
	        	 	}
	                JSONObject res= JSONObject.fromObject(object.get("result"));
	                JSONArray list= JSONArray.fromObject(res.get("list"));
	                for(int i=0;i<list.size();i++){
	                	JSONArray job = list.getJSONArray(i); // 遍历 list 数组，把每一个对象转成 json 对象
	                	String name=(String) job.get(0);
	                	BigDecimal rate=new BigDecimal(job.get(2).toString()).divide(new BigDecimal(100));
	                	//相对人民币的利率
	                	if(log.isInfoEnabled()){
		        	 		log.info("利率："+name+rate);
		        	 	}
	                	resulMap.put(name, rate);
	                }
	                
	         }else{
	                System.out.println(object.get("error_code")+":"+object.get("reason"));
	         }
	      } catch (Exception e) {
	            e.printStackTrace();
	      }
		 resulMap.put("人民币", new BigDecimal(1));
		 return resulMap;
	}
	
}
