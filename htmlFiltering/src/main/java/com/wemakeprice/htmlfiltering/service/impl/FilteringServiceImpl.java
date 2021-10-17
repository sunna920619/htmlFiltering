package com.wemakeprice.htmlfiltering.service.impl;

import java.util.Arrays;
import java.util.Comparator;

import com.wemakeprice.htmlfiltering.exception.ExceptionEnum;
import com.wemakeprice.htmlfiltering.exception.FilteringApiException;
import com.wemakeprice.htmlfiltering.service.FilteringService;

public class FilteringServiceImpl implements FilteringService {
	
	private static final String WITHOUT_TAG = "01";
	private static final String WITH_TAG = "02";
	
	@Override
	public String filterHtmlText(String htmlText, String type) {

		// type�� ���� �±� �� Ư������ ����
		String filtered = filterByType(htmlText, type);
		
		// ���� Ȥ�� ���� ����
		String alphabetText = filtered.replaceAll("[^a-zA-Z]", "");
		String numberText = filtered.replaceAll("[^0-9]", ""); // ���ڸ� ����

		String[] alphabet = alphabetText.split("");
		String[] numbers = numberText.split("");
		
		// ���� & ���� �䱸���׿� ���� sorting
		sortAlphabet(alphabet);
		sortNumbers(numbers);
		
		// ����-���� ������ ��ġ��
		return combineAlphabetNumber(alphabet, numbers);
	}

	public String combineAlphabetNumber(String[] alphabet, String[] numbers) {
		StringBuilder sb = new StringBuilder();
		
		int alphabetLength = alphabet != null ? alphabet.length : 0;
		int numbersLength = numbers != null ? numbers.length : 0;
		
		int index = 0;
		while (index < alphabetLength || index < numbersLength) {
			if (index < alphabetLength) {
				sb.append(alphabet[index]);
			}
			
			if (index < numbersLength) {
				sb.append(numbers[index]);
			}
			
			index++;
		}
		
		
		return sb.toString();
	}

	public String filterByType(String htmlText, String type) {
		String regex = "";
		
		if (WITHOUT_TAG.equals(type)) {
			// HTML �±� ����
			regex = "<([^>]+)>|[^0-9a-zA-Z]|\\s";
		} else if (WITH_TAG.equals(type)){
			// TEXT ��ü (����, ���ڸ�)
			regex = "[^0-9a-zA-Z]|\\s";
		} else {
			throw new FilteringApiException(ExceptionEnum.TYPE_IS_WRONG);
		}
		
		return htmlText.replaceAll(regex, "");
	}

	public void sortAlphabet(String[] alphabet) {
		Arrays.sort(alphabet, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String o1Upper = o1.toUpperCase();
				String o2Upper = o2.toUpperCase();

				int result = o1Upper.compareTo(o2Upper);

				if(result != 0)
					return result;
				else{
					return o1.compareTo(o2);
				}
			}
		});
	}

	public void sortNumbers(String[] numbers) {
		Arrays.sort(numbers);
	}
	
}
