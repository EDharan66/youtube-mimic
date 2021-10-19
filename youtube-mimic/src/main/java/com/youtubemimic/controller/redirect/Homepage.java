package com.youtubemimic.controller.redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "Homepage",
    urlPatterns = {"/v1/home"}
)
public class Homepage extends HttpServlet {
    public Homepage() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/homepageV2.html").include(request, response);
    }
}
