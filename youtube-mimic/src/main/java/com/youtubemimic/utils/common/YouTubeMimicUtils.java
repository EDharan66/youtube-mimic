package com.youtubemimic.utils.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youtubemimic.bean.ApiResponce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class YouTubeMimicUtils {

    public static void apiResponseWriter(HttpServletResponse response, String status_code, String message, String status, HashMap<String, Object> details, int statusCode) {
        writeResponse(response, new ApiResponce(status_code, message, status, details), statusCode);
    }

    public static void writeResponse(HttpServletResponse response, Object resObject, int statusCode) {
        try {
            PrintWriter out = response.getWriter();
            response.setStatus(statusCode);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print((new Gson()).toJson(resObject));
            out.flush();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static String getRequestBody(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();

        try {
            BufferedReader reader = request.getReader();

            String line;
            while((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception var4) {
        }
        return jb.toString();
    }

    public static Object getRequestBodyURL(HttpURLConnection connection) throws JsonParseException, IOException {
        StringBuilder jb = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception var4) {
        }
//        JsonObject jsonObjectAlt = JsonParser.parseString(jb.toString()).getAsJsonObject();

        return JsonParser.parseString(jb.toString()).getAsJsonObject();
    }

    public static void writeResponseJson(HttpServletResponse response, JsonObject jsonObject, int statusCode) {
        try {
            PrintWriter out = response.getWriter();
            response.setStatus(statusCode);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonObject);
            out.flush();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    public static void signOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userId = (String)session.getAttribute("empId");
            session.invalidate();
            HashMap<String, Object> detailsMap = new HashMap<String, Object>();
            detailsMap.put("id", userId);
            apiResponseWriter(response, "SUCCESS", "successfully sign out", "success", detailsMap, 200);
        }

    }
}
