package com.wemakeprice.htmlfiltering.service.impl;


import com.wemakeprice.htmlfiltering.domain.HtmlFilteringOut;
import com.wemakeprice.htmlfiltering.service.DivideService;

public class DivideServiceImpl implements DivideService {
	
	@Override
	public HtmlFilteringOut divideText(String text, int divisor) {
		HtmlFilteringOut out = new HtmlFilteringOut("", "");
		
		if (text == null) {
			return out;
		}
		
		int substringIndex = text.length() / divisor * divisor;
		
		if (substringIndex > 0) {
			out.setMainText(text.substring(0, substringIndex));
		}
		
		out.setRemainderText(text.substring(substringIndex));
		
		return out;
	}
}
