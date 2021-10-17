package com.wemakeprice.htmlfiltering.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import com.wemakeprice.htmlfiltering.exception.FilteringApiException;

class ValidatorTest {

	@ParameterizedTest
	@NullSource
	@EmptySource
	void testValidateInput_Url_��(String url) {
		assertThatThrownBy(() -> Validator.validateInput(url, "", 1))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("URL�� �Է��ؾ� �մϴ�.");
	}
	
	@Test
	void testValidateInput_�߸���_Url() {
		assertThatThrownBy(() -> Validator.validateInput("AAA", "", 1))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("�߸��� Url �Դϴ�.");
	}
	
	@ParameterizedTest
	@NullSource
	@EmptySource
	void testValidateInput_Type_��(String type) {
		assertThatThrownBy(() -> Validator.validateInput("https://naver.com", type, 1))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("Type�� �Է��ؾ� �մϴ�.");
	}
	
	@Test
	void testValidateInput_Divisor_ZERO() {
		assertThatThrownBy(() -> Validator.validateInput("https://naver.com", "02", 0))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("��� ���� ������ 1 �̻����� �Է��ؾ� �մϴ�.");
	}

}
