package com.wemakeprice.htmlfiltering.service.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wemakeprice.htmlfiltering.exception.FilteringApiException;

class FilteringServiceImplTest {
	
	FilteringServiceImpl service = new FilteringServiceImpl();
	
	@Test
	void testFilterHtmlText_태그_특수문자_제외() {
		assertEquals("A0a1a7BbZz", service.filterHtmlText("<tag>김#z0A</tag>aa17bBZ?", "01"));
	}
	
	@Test
	void testFilterHtmlText_태그_포함_특수문자_제외() {
		assertEquals("A0a1a7BbiillZz", service.filterHtmlText("김<li>#z0Aaa17bB\nZ?</li>", "02"));
	}

	@Test
	void testCombineAlphabetNumber_영문_없음() {
		String[] alphabet = new String[] {};
		String[] numbers = new String[] {"1", "2", "3", "4", "4"};
		assertEquals("12344", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	@Test
	void testCombineAlphabetNumber_숫자_없음() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C", "R", "r"};
		String[] numbers = new String[] {};
		assertEquals("AaabCRr", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	
	@Test
	void testCombineAlphabetNumber_영문_숫자_갯수_동일() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C"};
		String[] numbers = new String[] {"1", "2", "3", "4", "4"};
		assertEquals("A1a2a3b4C4", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	@Test
	void testCombineAlphabetNumber_영문이_숫자보다_갯수_많음() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C", "F", "Z", "z"};
		String[] numbers = new String[] {"1", "2", "3", "4", "4"};
		assertEquals("A1a2a3b4C4FZz", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	@Test
	void testCombineAlphabetNumber_숫자가_영문보다_갯수_많음() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C"};
		String[] numbers = new String[] {"1", "2", "3", "4", "4", "7", "7", "9"};
		assertEquals("A1a2a3b4C4779", service.combineAlphabetNumber(alphabet, numbers));
	}


	@Test
	void testFilterByType_태그_제외() {
		String htmlText = "<html></html>";
		String type = "01";
		assertEquals("", service.filterByType(htmlText, type));
	}
	
	@Test
	void testFilterByType_태그_특수문자_제외() {
		String htmlText = "<html>0START<tag>This has to be returned.....</tag>\n\t\rEND1</html>";
		String type = "01";
		assertEquals("0STARTThishastobereturnedEND1", service.filterByType(htmlText, type));
	}
	
	@Test
	void testFilterByType_텍스트_전체_특수문자_제외() {
		String htmlText = "<html>0START<tag>This has to be returned.....</tag>\n\t\rEND1</html>";
		String type = "02";
		assertEquals("html0STARTtagThishastobereturnedtagEND1html", service.filterByType(htmlText, type));
	}
	@Test
	void testFilterByType_존재하지_않는_유형_에러() {
		assertThatThrownBy(() -> service.filterByType("aaa", "99"))
        		.isInstanceOf(FilteringApiException.class)
        		.hasMessageContaining("존재하지 않는 Type이 입력되었습니다.");
	}
	
	
	@Test
	void testSortAlphabet_알파벳_정렬_소문자() {
		String[] alphabet = new String[] {"a", "f", "e", "d", "y", "z", "y", "k"};
		service.sortAlphabet(alphabet);
		assertThat(alphabet).containsExactly("a", "d", "e", "f", "k", "y", "y", "z");
	}

	@Test
	void testSortAlphabet_알파벳_정렬_대문자() {
		String[] alphabet = new String[] {"A", "D", "D", "T", "Q", "W", "W", "W"};
		service.sortAlphabet(alphabet);
		assertThat(alphabet).containsExactly("A", "D", "D", "Q", "T", "W", "W", "W");
	}
	
	@Test
	void testSortAlphabet_알파벳_정렬_소문자_대문자() {
		String[] alphabet = new String[] {"D", "a", "A", "B", "B", "Q", "b", "c", "Q"};
		service.sortAlphabet(alphabet);
		assertThat(alphabet).containsExactly("A", "a", "B", "B", "b", "c", "D", "Q", "Q");
	}

	
	@Test
	void testSortNumbers_숫자_정렬() {
		String[] numbers = new String[] {"1", "9", "8", "0", "1", "2", "8", "0"};
		service.sortNumbers(numbers);
		assertThat(numbers).containsExactly("0", "0", "1", "1", "2", "8", "8", "9");
	}

}
