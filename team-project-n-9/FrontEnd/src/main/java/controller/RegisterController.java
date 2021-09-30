package controller;//import static app.Main.bookDao;
//import static app.Main.userDao;

import java.util.HashMap;
import java.util.Map;

//import app.controller.paths.Template;
//import app.controller.utils.ViewUtil;
import dao.UserDao;

import io.javalin.http.Handler;
import model.User;

public class RegisterController {
	
	public static Handler handleRegisterGet = ctx -> {
		Map<String, Object> model = new HashMap<String ,Object>();
    	//model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/user/register.vm",model);
    };

	public static  Handler handleRegisterPost = ctx -> {
        User newUser = new User(
                ctx.formParam("email"),
                ctx.formParam("pass"),
                ctx.formParam("firstname"),
                ctx.formParam("lastname"),
                ctx.formParam("gender"),
                ctx.formParam("dob"),
                ctx.formParam("country"),
                ctx.formParam("accountType"));
        UserDao.addUser(newUser);

        //Temp code for printing all users
//        StringBuilder sb = new StringBuilder();
//        for(User s: UserDao.readDatabase()){
//            sb.append(s.getFirstname() + " " + s.getLastname() + "<br>");
//        }
        Map<String, Object> model = new HashMap<String ,Object>();
    	//model.put("CURRENT_USER", "Logged in as: " + UserDao.getCurrentUserName());
        model.put("CURRENT_USER", UserDao.getCurrentUser());
        ctx.render("/public/index.vm", model);

    };
}
