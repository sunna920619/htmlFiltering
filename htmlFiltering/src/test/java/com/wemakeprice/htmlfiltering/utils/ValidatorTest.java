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
	void testValidateInput_Url_빈값(String url) {
		assertThatThrownBy(() -> Validator.validateInput(url, "", 1))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("URL을 입력해야 합니다.");
	}
	
	@Test
	void testValidateInput_잘못된_Url() {
		assertThatThrownBy(() -> Validator.validateInput("AAA", "", 1))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("잘못된 Url 입니다.");
	}
	
	@ParameterizedTest
	@NullSource
	@EmptySource
	void testValidateInput_Type_빈값(String type) {
		assertThatThrownBy(() -> Validator.validateInput("https://naver.com", type, 1))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("Type을 입력해야 합니다.");
	}
	
	@Test
	void testValidateInput_Divisor_ZERO() {
		assertThatThrownBy(() -> Validator.validateInput("https://naver.com", "02", 0))
			.isInstanceOf(FilteringApiException.class)
			.hasMessageContaining("출력 단위 묶음은 1 이상으로 입력해야 합니다.");
	}

}
