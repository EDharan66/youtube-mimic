package com.youtubemimic.utils;

import com.google.gson.Gson;
import com.youtubemimic.bean.UserDataEntity;
import com.youtubemimic.service.ObjectifyWebListener;
import com.youtubemimic.utils.common.YouTubeMimicUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUtils {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public UserUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public static UserUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
        return new UserUtils(request, response);
    }

    private void loadSession(String value, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", value);
    }

    public void processLogin() {
        try {
            UserDataEntity entity = getInputData();
            UserDataEntity dbEntity = (UserDataEntity)ObjectifyWebListener.ofy().load().type(UserDataEntity.class).filter("emailId", entity.getEmailId()).filter("password", entity.getPassword()).first().now();
            this.loadSession(dbEntity.getUserId().toString(), this.request);
            YouTubeMimicUtils.apiResponseWriter(this.response, "SUCCESS", "successfully login", "success", null, 200);
            return;
        } catch (Exception var3) {
            YouTubeMimicUtils.apiResponseWriter(this.response, "ERROR", "failed login", "error", null, 400);
            throw var3;
        }
    }

    public void processSignUp() {
        try {
            UserDataEntity entity = getInputData();
            entity.setImgUrl(UploadBucketImage.getInstance().uploadImage(entity.getImgName(), entity.getImgUrl()));
            ObjectifyWebListener.ofy().save().entities(new UserDataEntity[]{entity}).now();
            this.loadSession(entity.getUserId().toString(), this.request);
            YouTubeMimicUtils.apiResponseWriter(this.response, "SUCCESS", "successfully sign up", "success", null, 200);
            return;
        } catch (Exception var2) {
            YouTubeMimicUtils.apiResponseWriter(this.response, "ERROR", "failed sign up", "error", null, 400);
            throw var2;
        }
    }
    
    private UserDataEntity getInputData() {
		return (new Gson()).fromJson(YouTubeMimicUtils.getRequestBody(this.request), UserDataEntity.class);
	}
}
