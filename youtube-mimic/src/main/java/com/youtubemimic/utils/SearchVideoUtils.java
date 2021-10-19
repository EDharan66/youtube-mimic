package com.youtubemimic.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.youtubemimic.utils.common.YouTubeMimicUtils;
import static com.youtubemimic.auth.UserPropertyConstant.API_KEY;

public class SearchVideoUtils {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private static final String PART = "id,snippet";
    private static final String TYPE = "video";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 24;

    public SearchVideoUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static SearchVideoUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new SearchVideoUtils(request, response);
    }

    public void searchList() throws IOException {
        try {
            String keyword = this.request.getParameter("keyword");
            HttpURLConnection connection = (HttpURLConnection)this.searchByKeyWord(keyword).openConnection();
            connection.connect();
            YouTubeMimicUtils.writeResponse(this.response, YouTubeMimicUtils.getRequestBodyURL(connection), 200);
        } catch (IOException var3) {
            YouTubeMimicUtils.apiResponseWriter(this.response, "ERROR", "failed load data", "error", null, 400);
            throw var3;
        }
    }

    public void videoList() throws IOException {
        try {
            String videoId = this.request.getParameter("videoId");
            HttpURLConnection connection = (HttpURLConnection)this.searchByVideoId(videoId).openConnection();
            connection.connect();
            YouTubeMimicUtils.writeResponse(this.response, YouTubeMimicUtils.getRequestBodyURL(connection), 200);
        } catch (IOException var3) {
            YouTubeMimicUtils.apiResponseWriter(this.response, "ERROR", "failed load data", "error", null, 400);
            throw var3;
        }
    }

    private URL searchByKeyWord(String queryTerm) throws IOException {
        return new URL("https://www.googleapis.com/youtube/v3/search?part="+PART+",snippet&key="+API_KEY+"&type="+TYPE+"&maxResults="+NUMBER_OF_VIDEOS_RETURNED+"&q=" + queryTerm);
    }

    private URL searchByVideoId(String videoId) throws IOException {
        return new URL("https://www.googleapis.com/youtube/v3/videos?part="+PART+",snippet&key="+API_KEY+"&id=" + videoId);
    }
}
