package com.youtubemimic.controller;

import com.youtubemimic.utils.SearchVideoUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "VideoList",
    urlPatterns = {"/v1/videoList"}
)
public class VideoList extends HttpServlet {
    public VideoList() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SearchVideoUtils.getInstance(request, response).videoList();
    }
}
