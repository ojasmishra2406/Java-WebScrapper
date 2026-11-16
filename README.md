# 🕸️ WebScraper Pro

[![Java](https://img.shields.io/badge/Java-21%20%7C%2025-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Jsoup](https://img.shields.io/badge/Jsoup-1.21.2-red?style=for-the-badge&logo=html5&logoColor=white)](https://jsoup.org/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.3-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)](https://getbootstrap.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](LICENSE)
[![Author](https://img.shields.io/badge/Author-Ojas%20Mishra-brightgreen?style=for-the-badge)](mailto:mishraojas2406@gmail.com)

> **WebScraper Pro** is a high-performance, full-stack web scraping and content extraction engine developed by **Ojas Mishra**. It combines Spring Boot RESTful services with Jsoup DOM traversal and a modern, responsive Bootstrap 5 frontend to inspect, extract, and categorize web assets (images, hyperlinks, videos, and page metadata) in real time.

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Key Features](#-key-features)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Project Directory Structure](#-project-directory-structure)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation & Execution](#installation--execution)
- [REST API Reference](#-rest-api-reference)
  - [GET /api/scrape](#get-apiscrape)
- [Web Interface](#-web-interface)
- [Author & Credits](#-author--credits)
- [License](#-license)

---

## 🚀 Overview

Modern web scraping requires handling unpredictable markup, relative URL resolutions, lazy-loaded media attributes, and asynchronous client consumption. **WebScraper Pro** addresses these challenges through a modular Java backend paired with an intuitive UI:

1. **Protocol Normalization**: Automatically normalizes user inputs (prepending protocols when omitted, validating schemes).
2. **DOM Parsing & Traversal**: Leverages Jsoup CSS selector querying to extract DOM nodes with robust absolute URL resolution (`absUrl`).
3. **Deduplication Engine**: Uses linked hash sets to preserve discovery order while stripping redundant links and resources.
4. **Rich Client Dashboard**: Presents categorized assets across clean tabs with direct link copying, image previews, error handling, and one-click JSON export.

---

## ✨ Key Features

- **Automated Media Extraction**:
  - **Images**: Extracts `src`, `data-src` (for lazy-loaded assets), resolving relative paths into fully qualified absolute URLs.
  - **Videos & Embeds**: Captures direct HTML5 `<video src>`, child `<source src>` nodes, and streaming iframe embeds (YouTube, Vimeo).
  - **Hyperlinks**: Extracts all anchor links (`<a href>`), stripping empty anchors, `javascript:` pseudoprotocols, and telephone/mail links.
- **Graceful Error Handling**: Comprehensive validation prevents application crashes on malformed URLs, unresponsive hosts, or timeouts.
- **Modern Bootstrap 5 UI**:
  - Live metric counters (Total Images, Total Links, Total Videos).
  - Quick-sample launch pills for instant testing (Hacker News, Wikipedia, GitHub).
  - Searchable links list with integrated one-click clipboard copying.
  - Interactive image gallery with fallback error states.
  - Instant client-side JSON export for data ingestion.
- **Robust REST API**: Decoupled `@RestController` allowing programmatic consumption by external scripts, CLI utilities, or frontend SPAs.

---

## 🏗️ System Architecture

```text
[ Browser Client / User ]
         │
         │  HTTP GET /api/scrape?url=...
         ▼
[ Spring Boot Controller: ScrapeController ]
         │
         │  Delegates to Service Layer
         ▼
[ ScraperService (Business Logic) ]
         │
         ├── 1. URL Normalization & Validation
         ├── 2. Jsoup HTTP Connection (Custom User-Agent & 10s Timeout)
         ├── 3. DOM Tree Parsing & CSS Selection
         └── 4. Relative-to-Absolute Resolution & Deduplication
         │
         ▼
[ ScrapeResponse DTO (Model) ]
         │
         ▼
[ JSON Serialization / Thymeleaf UI View ]
```

---

## 🛠️ Tech Stack

| Layer | Technology | Version | Purpose |
| :--- | :--- | :--- | :--- |
| **Language** | Java (OpenJDK) | 21 / 25 | Core object-oriented runtime and standard libraries |
| **Backend Framework** | Spring Boot | 4.0.0 | Application scaffolding, MVC routing, dependency injection |
| **Web Parser** | Jsoup | 1.21.2 | HTML document parsing, DOM traversal, CSS selector queries |
| **Embedded Server** | Apache Tomcat | 11.0.x | Embedded high-performance servlet engine |
| **Frontend Framework** | Bootstrap 5 | 5.3.3 | Responsive grid, cards, tab navigation, and UI components |
| **UI Icons** | Bootstrap Icons | 1.11.3 | Modern vector icons for UI aesthetics |
| **Build Tool** | Apache Maven | 3.9.x (Wrapper) | Project dependency management, compilation, and testing |
| **Testing** | JUnit 5 & Mockito | Latest | Unit testing and Spring Boot integration tests |

---

## 📁 Project Directory Structure

```text
Java-WebScrapper/
├── .mvn/wrapper/                 # Maven wrapper configuration
├── src/
│   ├── main/
│   │   ├── java/com/WebScrapping/
│   │   │   ├── Controller/
│   │   │   │   └── ScrapeController.java     # REST API endpoint handler
│   │   │   ├── model/
│   │   │   │   └── ScrapeResponse.java       # DTO representing scraped payload
│   │   │   ├── Service/
│   │   │   │   └── ScraperService.java       # Jsoup DOM extraction engine
│   │   │   └── WebScrappingApplication.java  # Spring Boot main entry point
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── index.html                # Responsive web dashboard
│   │       └── application.properties        # App configuration (port: 8181)
│   └── test/
│       └── java/com/WebScrapping/
│           └── WebScrappingApplicationTests.java # Automated integration tests
├── .gitignore
├── mvnw & mvnw.cmd               # Maven wrapper execution scripts
├── pom.xml                       # Maven Project Object Model dependencies
└── README.md                     # Comprehensive project documentation
```

---

## 💻 Getting Started

### Prerequisites

Ensure you have the following installed on your machine:
- **JDK 17, 21, or 25** (`java -version`)
- **Git** (`git --version`)

### Installation & Execution

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/mishraojas2406/Java-WebScrapper.git
   cd Java-WebScrapper
   ```

2. **Build and Run Unit Tests**:
   - **Linux / macOS**:
     ```bash
     ./mvnw clean test
     ```
   - **Windows**:
     ```powershell
     .\mvnw.cmd clean test
     ```

3. **Launch the Application**:
   - **Linux / macOS**:
     ```bash
     ./mvnw spring-boot:run
     ```
   - **Windows**:
     ```powershell
     .\mvnw.cmd spring-boot:run
     ```

4. **Access the Web Dashboard**:
   Open your browser and navigate to:
   ```text
   http://localhost:8181
   ```

---

## 🔌 REST API Reference

### `GET /api/scrape`

Extracts images, videos, hyperlinks, and page title from a target URL.

#### Query Parameters:
| Parameter | Type | Required | Description |
| :--- | :--- | :--- | :--- |
| `url` | `String` | Yes | Target website URL (e.g. `news.ycombinator.com` or `https://github.com`) |

#### Example Request:
```bash
curl -X GET "http://localhost:8181/api/scrape?url=https://news.ycombinator.com"
```

#### Example Success Response (`200 OK`):
```json
{
  "url": "https://news.ycombinator.com",
  "title": "Hacker News",
  "status": "success",
  "message": "Successfully extracted web contents",
  "totalImages": 2,
  "totalLinks": 195,
  "totalVideos": 0,
  "images": [
    "https://news.ycombinator.com/y18.svg",
    "https://news.ycombinator.com/s.gif"
  ],
  "links": [
    "https://news.ycombinator.com",
    "https://news.ycombinator.com/news",
    "https://news.ycombinator.com/newest"
  ],
  "videos": []
}
```

#### Example Error Response (`400 Bad Request`):
```json
{
  "url": null,
  "title": null,
  "status": "error",
  "message": "URL parameter is required and cannot be empty.",
  "totalImages": 0,
  "totalLinks": 0,
  "totalVideos": 0,
  "images": [],
  "links": [],
  "videos": []
}
```

---

## 🎨 Web Interface

The user interface provides:
- **Instant Extraction**: Input box with Enter key trigger and clear button.
- **Metric Badges**: Real-time summary counts for extracted images, links, and media.
- **Interactive Tabs**:
  - **Images**: Responsive thumbnail grid with click-to-view and fallback handling.
  - **Links**: Searchable list with one-click clipboard copy.
  - **Videos**: Native HTML5 players and embedded iframe players.
  - **JSON Viewer**: Raw structured data view with instant file download capability.

---

## 👨‍💻 Author & Credits

- **Developer**: **Ojas Mishra**
- **Email**: [mishraojas2406@gmail.com](mailto:mishraojas2406@gmail.com)
- **Role**: Software Engineer / Full-Stack Java Developer

---

## 📄 License

This project is open-source and licensed under the [MIT License](LICENSE).
