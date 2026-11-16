package com.WebScrapping.model;

import java.util.ArrayList;
import java.util.List;

public class ScrapeResponse {

    private String url;
    private String title;
    private String status;
    private String message;
    private List<String> images = new ArrayList<>();
    private List<String> videos = new ArrayList<>();
    private List<String> links = new ArrayList<>();
    private int totalImages;
    private int totalVideos;
    private int totalLinks;

    public ScrapeResponse() {
    }

    public ScrapeResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
        this.totalImages = (images != null) ? images.size() : 0;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
        this.totalVideos = (videos != null) ? videos.size() : 0;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
        this.totalLinks = (links != null) ? links.size() : 0;
    }

    public int getTotalImages() {
        return totalImages;
    }

    public int getTotalVideos() {
        return totalVideos;
    }

    public int getTotalLinks() {
        return totalLinks;
    }
}
