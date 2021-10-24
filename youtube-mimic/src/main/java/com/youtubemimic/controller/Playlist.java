package com.youtubemimic.controller;

import com.youtubemimic.utils.PlaylistUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "PlayList",
    urlPatterns = {"/v1/playlist"}
)
public class Playlist extends HttpServlet {
    public Playlist() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
			try {
				PlaylistUtils.getInstance(request, response).processGet();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			PlaylistUtils.getInstance(request, response).processPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			PlaylistUtils.getInstance(request, response).processPut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			PlaylistUtils.getInstance(request, response).processDelete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}