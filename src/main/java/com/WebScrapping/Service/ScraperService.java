package com.WebScrapping.Service;

import com.WebScrapping.model.ScrapeResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScraperService {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
    private static final int TIMEOUT_MS = 10000;

    public ScrapeResponse webScrape(String rawUrl) throws IOException {
        String url = normalizeUrl(rawUrl);

        Document doc = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(TIMEOUT_MS)
                .referrer("https://www.google.com")
                .ignoreHttpErrors(false)
                .get();

        Set<String> imagesSet = new LinkedHashSet<>();
        Set<String> videosSet = new LinkedHashSet<>();
        Set<String> linksSet = new LinkedHashSet<>();

        // 1. Extract Images (src, data-src, srcset)
        Elements imgElements = doc.select("img");
        for (Element img : imgElements) {
            String src = img.absUrl("src");
            if (src.isEmpty()) {
                src = img.absUrl("data-src");
            }
            if (!src.isEmpty() && isValidHttpUrl(src)) {
                imagesSet.add(src);
            }
        }

        // 2. Extract Videos (video src, source src inside video, iframe src for embed players)
        Elements videoElements = doc.select("video");
        for (Element video : videoElements) {
            String src = video.absUrl("src");
            if (!src.isEmpty() && isValidHttpUrl(src)) {
                videosSet.add(src);
            }
            for (Element source : video.select("source")) {
                String sourceSrc = source.absUrl("src");
                if (!sourceSrc.isEmpty() && isValidHttpUrl(sourceSrc)) {
                    videosSet.add(sourceSrc);
                }
            }
        }

        // Also check iframe embeds (e.g., youtube, vimeo, dailymotion)
        Elements iframes = doc.select("iframe[src]");
        for (Element iframe : iframes) {
            String src = iframe.absUrl("src");
            if (!src.isEmpty() && (src.contains("youtube.com/embed") || src.contains("player.vimeo.com") || src.contains(".mp4") || src.contains(".webm"))) {
                videosSet.add(src);
            }
        }

        // 3. Extract Links
        Elements anchorElements = doc.select("a[href]");
        for (Element link : anchorElements) {
            String href = link.absUrl("href");
            if (!href.isEmpty() && isValidHttpUrl(href) && !href.startsWith("javascript:") && !href.startsWith("mailto:") && !href.startsWith("tel:")) {
                linksSet.add(href);
            }
        }

        ScrapeResponse response = new ScrapeResponse();
        response.setUrl(url);
        response.setTitle(doc.title() != null && !doc.title().trim().isEmpty() ? doc.title().trim() : "No Title Detected");
        response.setStatus("success");
        response.setMessage("Successfully extracted web contents");
        response.setImages(new ArrayList<>(imagesSet));
        response.setVideos(new ArrayList<>(videosSet));
        response.setLinks(new ArrayList<>(linksSet));

        return response;
    }

    private String normalizeUrl(String input) {
        if (input == null) {
            throw new IllegalArgumentException("URL cannot be empty");
        }
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }
        if (!trimmed.toLowerCase().startsWith("http://") && !trimmed.toLowerCase().startsWith("https://")) {
            return "https://" + trimmed;
        }
        return trimmed;
    }

    private boolean isValidHttpUrl(String url) {
        try {
            URL u = new URL(url);
            return "http".equalsIgnoreCase(u.getProtocol()) || "https".equalsIgnoreCase(u.getProtocol());
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
