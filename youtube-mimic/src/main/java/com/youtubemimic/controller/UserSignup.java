package com.youtubemimic.controller;

import com.youtubemimic.utils.UserUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "UserSignup",
    urlPatterns = {"/user/v1/signup"}
)
public class UserSignup extends HttpServlet {
    public UserSignup() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserUtils.getInstance(request, response).processSignUp();
    }
}