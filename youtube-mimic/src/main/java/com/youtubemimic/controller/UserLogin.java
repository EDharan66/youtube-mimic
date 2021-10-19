package com.youtubemimic.controller;

import com.youtubemimic.utils.UserUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "UserSignIn",
    urlPatterns = {"/v1/login"}
)
public class UserLogin extends HttpServlet {
    public UserLogin() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserUtils.getInstance(request, response).processLogin();
    }
}
