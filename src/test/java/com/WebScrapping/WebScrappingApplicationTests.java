package com.WebScrapping;

import com.WebScrapping.Controller.ScrapeController;
import com.WebScrapping.Service.ScraperService;
import com.WebScrapping.model.ScrapeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebScrappingApplicationTests {

	@Autowired
	private ScrapeController scrapeController;

	@Autowired
	private ScraperService scraperService;

	@Test
	void contextLoads() {
		assertNotNull(scrapeController);
		assertNotNull(scraperService);
	}

	@Test
	void testScrapeEmptyUrl() {
		ResponseEntity<ScrapeResponse> response = scrapeController.scrape("");
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("error", response.getBody().getStatus());
	}

	@Test
	void testScrapeNullUrl() {
		ResponseEntity<ScrapeResponse> response = scrapeController.scrape(null);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("error", response.getBody().getStatus());
	}
}
