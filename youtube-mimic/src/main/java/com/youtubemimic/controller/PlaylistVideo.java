package com.youtubemimic.controller;

import com.youtubemimic.utils.PlaylistUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "PlayListVideo",
    urlPatterns = {"/playlist/v1/video"}
)
public class PlaylistVideo extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			PlaylistUtils.getInstance(request, response).processVideoDelete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
