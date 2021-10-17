package com.wemakeprice.htmlfiltering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wemakeprice.htmlfiltering.domain.HtmlFilteringOut;
import com.wemakeprice.htmlfiltering.service.DivideService;
import com.wemakeprice.htmlfiltering.service.FilteringService;
import com.wemakeprice.htmlfiltering.service.HttpConnectionService;
import com.wemakeprice.htmlfiltering.utils.Validator;

@RestController
@RequestMapping("api")
public class HtmlFilteringController {
	
	@Autowired HttpConnectionService httpConnectionService;
	@Autowired FilteringService filteringService;
	@Autowired DivideService divideService;
	
	@GetMapping("/filter")
	public HtmlFilteringOut filterHtml(	@RequestParam String url,
										@RequestParam String type,
										@RequestParam int divisor) {
		Validator.validateInput(url, type, divisor);
		
		String htmlText = httpConnectionService.getHtmlByUrl(url);
		System.out.println(htmlText);
		String filteredText = filteringService.filterHtmlText(htmlText, type);
		
		return divideService.divideText(filteredText, divisor);
	}
	
}
