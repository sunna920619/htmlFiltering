package com.wemakeprice.htmlfiltering.utils;

import com.wemakeprice.htmlfiltering.exception.ExceptionEnum;
import com.wemakeprice.htmlfiltering.exception.FilteringApiException;

public class Validator {
	
	public static void validateInput(String url, String type, int divisor) {
		if (url == null || url.isBlank()) {
			throw new FilteringApiException(ExceptionEnum.INPUT_URL_IS_EMPTY);
		}
		
		if (!(url.startsWith("https://") || url.startsWith("http://"))) {
			throw new FilteringApiException(ExceptionEnum.INPUT_URL_IS_WRONG);
		}
		
		if (type == null || type.isBlank()) {
			throw new FilteringApiException(ExceptionEnum.TYPE_IS_EMPTY);
		}
		
		if (divisor < 1) {
			throw new FilteringApiException(ExceptionEnum.DIVISOR_IS_ZERO);
		}
	}
}
