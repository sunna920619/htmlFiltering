package com.wemakeprice.htmlfiltering.service.impl;

import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertTrue;

class HttpConnectionServiceTest {
	
	HttpConnectionService service = new HttpConnectionService();
	
	@Test
	void HTTP_URL_연결_성공() {
		String result = service.getHtmlByUrl("https://github.com/sunna920619/htmlFiltering/tree/master/htmlFiltering");
		assertTrue(result.contains("<!DOCTYPE html>"));
	}

}
