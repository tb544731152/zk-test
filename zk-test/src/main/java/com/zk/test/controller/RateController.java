package com.zk.test.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zk.test.domain.Rate;
import com.zk.test.result.Result;
import com.zk.test.service.RateService;
/**
 * /convert/USD/CNY
 * @author zhaok@zyzsbj.cn
 *
 */
@RestController
public class RateController {
	@Autowired
	private RateService rateService;
	
	@GetMapping("/convert/{from}/{to}")
    public Rate rate(@RequestParam Map<String, Object> paramMap,@PathVariable String from,@PathVariable String to) {
		BigDecimal amount = new BigDecimal(paramMap.get("amount").toString());
		return rateService.exchange(from, to, amount);
    }
    
    
    
    
}
