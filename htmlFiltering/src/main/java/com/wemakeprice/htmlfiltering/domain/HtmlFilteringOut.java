package com.wemakeprice.htmlfiltering.domain;

public class HtmlFilteringOut {
	private String mainText; 		// �� ���� ����
	private String remainderText; 	// ������
	
	public HtmlFilteringOut() {}
	
	public HtmlFilteringOut(String mainText, String remainderText) {
		this.mainText = mainText;
		this.remainderText = remainderText;
	}
	
	public String getMainText() {
		return mainText;
	}
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	public String getRemainderText() {
		return remainderText;
	}
	public void setRemainderText(String remainderText) {
		this.remainderText = remainderText;
	}
	
	
}
