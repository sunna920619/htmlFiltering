package com.wemakeprice.htmlfiltering.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.wemakeprice.htmlfiltering.exception.ExceptionEnum;
import com.wemakeprice.htmlfiltering.exception.FilteringApiException;
import com.wemakeprice.htmlfiltering.service.HttpConnectionService;

@Service
public class HttpConnectionServiceImpl implements HttpConnectionService {
	
	@Override
	public String getHtmlByUrl(String urlText) {
		
		URL url = null;
		HttpURLConnection conn = null;
	    
		BufferedReader br = null;
		StringBuffer out = null;
		
		try {
			url = new URL(urlText);	
			conn = (HttpURLConnection) url.openConnection();
	        
			//http ��û�� �ʿ��� Ÿ�� ���� �ǽ�
			conn.setRequestProperty("Accept", "text/html");	               
			conn.setRequestMethod("GET");	        	              
	        
			//http ��û �ǽ�
			conn.connect();
			
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				// ��û���� ��� output�� ���
				out = new StringBuffer();	  
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				
				String line;
				while ((line = br.readLine()) != null) {
					out.append(line);
				}
				
				return out.toString();
				
			} else {
				throw new FilteringApiException(ExceptionEnum.URL_CONNECTION_FAILED);
			}

		} catch (IOException e) {
			throw new FilteringApiException(ExceptionEnum.URL_CONNECTION_FAILED);
		} finally { 
			//http ��û �� ���� �Ϸ� �� BufferedReader�� �ݾ��ݴϴ�
			try {
				if (br != null) {
					br.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}	
	}
	
}
