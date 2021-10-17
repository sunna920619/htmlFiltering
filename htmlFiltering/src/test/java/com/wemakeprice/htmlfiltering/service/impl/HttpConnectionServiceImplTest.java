package com.wemakeprice.htmlfiltering.service.impl;

import org.junit.jupiter.api.Test;

import com.wemakeprice.htmlfiltering.exception.FilteringApiException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static  org.junit.jupiter.api.Assertions.assertTrue;

class HttpConnectionServiceImplTest {
	
	HttpConnectionServiceImpl service = new HttpConnectionServiceImpl();
	
	@Test
	void HTTP_URL_연결_성공() {
		String result = service.getHtmlByUrl("https://github.com/sunna920619/htmlFiltering/tree/master/htmlFiltering");
		assertTrue(result.contains("<!DOCTYPE html>"));
	}
	
	@Test
	void HTTP_URL_연결_실패() {
		assertThatThrownBy(() -> service.getHtmlByUrl("http://github.com/sunna920619"))
				.isInstanceOf(FilteringApiException.class)
				.hasMessageContaining("입력된 URL에 연결 중 connection 에러가 발생했습니다.");
	}

}
