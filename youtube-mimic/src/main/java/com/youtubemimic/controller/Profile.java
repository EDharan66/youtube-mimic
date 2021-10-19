package com.youtubemimic.controller;

import com.youtubemimic.utils.UserProfileUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "Profile",
    urlPatterns = {"/v1/profile"}
)
public class Profile extends HttpServlet {
    public Profile() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfileUtils.getInstance(request, response).processGet();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserProfileUtils.getInstance(request, response).processPut();
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 UserProfileUtils.getInstance(request, response).processDelete();
    }
}
