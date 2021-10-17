package com.wemakeprice.htmlfiltering.service.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.wemakeprice.htmlfiltering.domain.HtmlFilteringOut;

class DivideServiceImplTest {
	
	DivideServiceImpl service = new DivideServiceImpl();
	
	@Test
	void testDivideText_text_null��_���() {
		HtmlFilteringOut result = service.divideText(null, 1);
		
		assertEquals("", result.getMainText());
		assertEquals("", result.getRemainderText());
	}
	
	@Test
	void testDivideText_text_��_��_���() {
		HtmlFilteringOut result = service.divideText("", 1);
		
		assertEquals("", result.getMainText());
		assertEquals("", result.getRemainderText());
	}
	
	@Test
	void testDivideText_������_����_1��_���() {
		HtmlFilteringOut result = service.divideText("1234567890", 1);
		
		assertEquals("1234567890", result.getMainText());
		assertEquals("", result.getRemainderText());
	}
	
	@Test
	void testDivideText_������_����_����_���() {
		HtmlFilteringOut result = service.divideText("1234567890", 3);
		
		assertEquals("123456789", result.getMainText());
		assertEquals("0", result.getRemainderText());
	}
	
	@Test
	void testDivideText_������_����_����_���() {
		HtmlFilteringOut result = service.divideText("abc", 3);
		
		assertEquals("abc", result.getMainText());
		assertEquals("", result.getRemainderText());
	}
	
	@Test
	void testDivideText_������_����_����_��������_���() {
		HtmlFilteringOut result = service.divideText("abcdefghi", 3);
		
		assertEquals("abcdefghi", result.getMainText());
		assertEquals("", result.getRemainderText());
	}
	
	@Test
	void testDivideText_������_����_ū_���() {
		HtmlFilteringOut result = service.divideText("1234567890", 100);
		
		assertEquals("", result.getMainText());
		assertEquals("1234567890", result.getRemainderText());
	}

}
