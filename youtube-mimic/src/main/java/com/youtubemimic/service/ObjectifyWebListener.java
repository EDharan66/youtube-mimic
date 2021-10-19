package com.youtubemimic.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.youtubemimic.bean.PlaylistEntity;
import com.youtubemimic.bean.UserDataEntity;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ObjectifyWebListener implements ServletContextListener {
    public ObjectifyWebListener() {
    }

    public void contextInitialized(ServletContextEvent event) {
        ObjectifyService.init();
        ObjectifyService.register(UserDataEntity.class);
        ObjectifyService.register(PlaylistEntity.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }
}
