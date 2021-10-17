package com.wemakeprice.htmlfiltering.service.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.wemakeprice.htmlfiltering.exception.FilteringApiException;

class FilteringServiceImplTest {
	
	FilteringServiceImpl service = new FilteringServiceImpl();
	
	@Test
	void testFilterHtmlText_�±�_Ư������_����() {
		assertEquals("A0a1a7BbZz", service.filterHtmlText("<tag>��#z0A</tag>aa17bBZ?", "01"));
	}
	
	@Test
	void testFilterHtmlText_�±�_����_Ư������_����() {
		assertEquals("A0a1a7BbiillZz", service.filterHtmlText("��<li>#z0Aaa17bB\nZ?</li>", "02"));
	}

	@Test
	void testCombineAlphabetNumber_����_����() {
		String[] alphabet = new String[] {};
		String[] numbers = new String[] {"1", "2", "3", "4", "4"};
		assertEquals("12344", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	@Test
	void testCombineAlphabetNumber_����_����() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C", "R", "r"};
		String[] numbers = new String[] {};
		assertEquals("AaabCRr", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	
	@Test
	void testCombineAlphabetNumber_����_����_����_����() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C"};
		String[] numbers = new String[] {"1", "2", "3", "4", "4"};
		assertEquals("A1a2a3b4C4", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	@Test
	void testCombineAlphabetNumber_������_���ں���_����_����() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C", "F", "Z", "z"};
		String[] numbers = new String[] {"1", "2", "3", "4", "4"};
		assertEquals("A1a2a3b4C4FZz", service.combineAlphabetNumber(alphabet, numbers));
	}
	
	@Test
	void testCombineAlphabetNumber_���ڰ�_��������_����_����() {
		String[] alphabet = new String[] {"A", "a", "a", "b", "C"};
		String[] numbers = new String[] {"1", "2", "3", "4", "4", "7", "7", "9"};
		assertEquals("A1a2a3b4C4779", service.combineAlphabetNumber(alphabet, numbers));
	}


	@Test
	void testFilterByType_�±�_����() {
		String htmlText = "<html></html>";
		String type = "01";
		assertEquals("", service.filterByType(htmlText, type));
	}
	
	@Test
	void testFilterByType_�±�_Ư������_����() {
		String htmlText = "<html>0START<tag>This has to be returned.....</tag>\n\t\rEND1</html>";
		String type = "01";
		assertEquals("0STARTThishastobereturnedEND1", service.filterByType(htmlText, type));
	}
	
	@Test
	void testFilterByType_�ؽ�Ʈ_��ü_Ư������_����() {
		String htmlText = "<html>0START<tag>This has to be returned.....</tag>\n\t\rEND1</html>";
		String type = "02";
		assertEquals("html0STARTtagThishastobereturnedtagEND1html", service.filterByType(htmlText, type));
	}
	@Test
	void testFilterByType_��������_�ʴ�_����_����() {
		assertThatThrownBy(() -> service.filterByType("aaa", "99"))
        		.isInstanceOf(FilteringApiException.class)
        		.hasMessageContaining("�������� �ʴ� Type�� �ԷµǾ����ϴ�.");
	}
	
	
	@Test
	void testSortAlphabet_���ĺ�_����_�ҹ���() {
		String[] alphabet = new String[] {"a", "f", "e", "d", "y", "z", "y", "k"};
		service.sortAlphabet(alphabet);
		assertThat(alphabet).containsExactly("a", "d", "e", "f", "k", "y", "y", "z");
	}

	@Test
	void testSortAlphabet_���ĺ�_����_�빮��() {
		String[] alphabet = new String[] {"A", "D", "D", "T", "Q", "W", "W", "W"};
		service.sortAlphabet(alphabet);
		assertThat(alphabet).containsExactly("A", "D", "D", "Q", "T", "W", "W", "W");
	}
	
	@Test
	void testSortAlphabet_���ĺ�_����_�ҹ���_�빮��() {
		String[] alphabet = new String[] {"D", "a", "A", "B", "B", "Q", "b", "c", "Q"};
		service.sortAlphabet(alphabet);
		assertThat(alphabet).containsExactly("A", "a", "B", "B", "b", "c", "D", "Q", "Q");
	}

	
	@Test
	void testSortNumbers_����_����() {
		String[] numbers = new String[] {"1", "9", "8", "0", "1", "2", "8", "0"};
		service.sortNumbers(numbers);
		assertThat(numbers).containsExactly("0", "0", "1", "1", "2", "8", "8", "9");
	}

}
