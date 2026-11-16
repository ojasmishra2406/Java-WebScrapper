package com.WebScrapping.Controller;

import com.WebScrapping.Service.ScraperService;
import com.WebScrapping.model.ScrapeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapeController {

    @Autowired
    private ScraperService scraperService;

    @GetMapping("/api/scrape")
    public ResponseEntity<ScrapeResponse> scrape(@RequestParam(required = false) String url) {
        if (url == null || url.trim().isEmpty()) {
            ScrapeResponse errorResponse = new ScrapeResponse("error", "URL parameter is required and cannot be empty.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        try {
            ScrapeResponse response = scraperService.webScrape(url);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ScrapeResponse errorResponse = new ScrapeResponse("error", "Invalid URL format: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            ScrapeResponse errorResponse = new ScrapeResponse("error", "Failed to scrape URL: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
