package controller;

import io.javalin.http.Handler;


import java.util.HashMap;
import dao.UserDao;

public class HomePageController {
    public static Handler homepageHandler = ctx -> {
        HashMap<String,Object> model = new HashMap<>();
        //model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/index.vm",model);
    };
}
