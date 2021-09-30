package controller;

import java.util.HashMap;
import java.util.Map;

import dao.UserDao;
import io.javalin.http.Handler;

public class LoginController {
    public static Handler loginHandler = ctx -> {
    	Map<String, Object> model = new HashMap<String ,Object>();
		model.put("CURRENT_USER", UserDao.getCurrentUser());
		ctx.render("/public/user/login.vm",model);
    };
    
    public static Handler handleLoginPage = ctx -> {
    	boolean checkExistence = false;
    	String userEmail = ctx.formParam("email");
    	String userPass = ctx.formParam("pass");

		checkExistence = UserDao.doesUserExist(userEmail, userPass);
		HashMap<String,Object> model = new HashMap<>();
		model = loginHelper(checkExistence, model);

		ctx.render("/public/index.vm", model);
    };

    //For testing purposes this code was made into a helper
    public static HashMap<String, Object> loginHelper(boolean checkExistence, HashMap model)
	{
		if (checkExistence) {
			model.put("CURRENT_USER", UserDao.getCurrentUser());
		}
		else{
			model.put("WARNING","No such login found");
		}

		return model;
	}

    public static Handler logOutHandler = ctx -> {
    	UserDao.logOut();
		ctx.render("/public/index.vm", logoutHelper(new HashMap()));
    };

    public static HashMap logoutHelper(HashMap model)
	{
			model.put("CURRENT_USER", UserDao.getCurrentUser());
		return model;
	}
}
