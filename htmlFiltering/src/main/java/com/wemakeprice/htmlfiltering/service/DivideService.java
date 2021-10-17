package com.wemakeprice.htmlfiltering.service;

import com.wemakeprice.htmlfiltering.domain.HtmlFilteringOut;

public interface DivideService {
	public HtmlFilteringOut divideText(String text, int divisor);
}
