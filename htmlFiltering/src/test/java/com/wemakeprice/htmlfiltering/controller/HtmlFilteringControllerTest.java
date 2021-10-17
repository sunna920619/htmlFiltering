package com.wemakeprice.htmlfiltering.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.wemakeprice.htmlfiltering.service.HttpConnectionService;
@SpringBootTest
@AutoConfigureMockMvc
class HtmlFilteringControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HttpConnectionService httpConnectionService;

	@Test
	void testFilterHtml_유형_01_출력_묶음_1() throws Exception {
		String url = "https://front.wemakeprice.com";
		given(httpConnectionService.getHtmlByUrl(url))
			.willReturn("<html>0ST9ART922234<tag>This has to be returned.....</tag>\\n\\t\\rEND1</html>");
		
		mockMvc.perform(get("/api/filter")
					.param("url", url).param("type", "01").param("divisor", "1"))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.mainText").value("A0a1b2D2d2E3e4e9e9hhiNnnoRrrrSssTTTtttu"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.remainderText").value(""));
	}
	
	@Test
	void testFilterHtml_유형_02_출력_묶음_1() throws Exception {
		String url = "https://front.wemakeprice.com";
		given(httpConnectionService.getHtmlByUrl(url))
			.willReturn("<html>0ST9ART922234<tag>This has to be returned.....</tag>\\n\\t\\rEND1</html>");
		
		mockMvc.perform(get("/api/filter")
					.param("url", url).param("type", "02").param("divisor", "1"))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.mainText").value("A0a1a2a2b2D3d4E9e9eegghhhhillmmNnnoRrrrSssTTTtttttttu"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.remainderText").value(""));
	}
	
	@Test
	void testFilterHtml_유형_01_출력_묶음_10() throws Exception {
		String url = "https://front.wemakeprice.com";
		given(httpConnectionService.getHtmlByUrl(url))
			.willReturn("<html>0ST9ART922234<tag>This has to be returned.....</tag>\\n\\t\\rEND1</html>");
		
		mockMvc.perform(get("/api/filter")
					.param("url", url).param("type", "01").param("divisor", "10"))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.mainText").value("A0a1b2D2d2E3e4e9e9hhiNnnoRrrrS"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.remainderText").value("ssTTTtttu"));
	}
	
	@Test
	void testFilterHtml_유형_02_출력_묶음_1000() throws Exception {
		String url = "https://front.wemakeprice.com";
		given(httpConnectionService.getHtmlByUrl(url))
			.willReturn("<html>0ST9ART922234<tag>This has to be returned.....</tag>\\n\\t\\rEND1</html>");
		
		mockMvc.perform(get("/api/filter")
					.param("url", url).param("type", "02").param("divisor", "1000"))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.mainText").value(""))
					.andExpect(MockMvcResultMatchers.jsonPath("$.remainderText").value("A0a1a2a2b2D3d4E9e9eegghhhhillmmNnnoRrrrSssTTTtttttttu"));
	}

}
