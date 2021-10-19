package com.youtubemimic.constant;

public class YoutubeMimicConstant {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String HOME_PAGE = "homepage.html";

    public static class ApiError {
        public static final String success = "success";
        public static final String error = "error";
    }

    public static class ApiStatusCode {
        public static final int OK = 200;
        public static final int CREATE = 201;
        public static final int FAILED = 400;
        public static final int AUTH_ERROR = 402;
        public static final int NOT_FOUND = 404;
    }

    public static class Basic {
        public static final String successfully_login = "successfully login";
        public static final String successfully_sign_up = "successfully sign up";
        public static final String successfully_load_data = "successfully load data";
        public static final String successfully_data_update = "successfully data updated";
        public static final String successfully_data_create = "successfully data created";
        public static final String successfully_data_delete = "successfully data deleted";
        public static final String successfully_delete = "successfully delete";
        public static final String failed_login = "failed login";
        public static final String failed_delete = "failed delete";
        public static final String failed_sign_up = "failed sign up";
        public static final String failed_create_data = "failed create data";
        public static final String failed_load_data = "failed load data";
        public static final String failed_data_update = "failed data update";
        public static final String failed_data_delete = "failed data to delete";
        public static final String failed_get = "failed getting data from DB";
        public static final String successfully_sign_out = "successfully sign out";
        public static final String ID = "id";
        public static final String USER_Id = "userId";
    }
}
