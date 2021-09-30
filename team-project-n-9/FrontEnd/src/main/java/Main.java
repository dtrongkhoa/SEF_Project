import controller.HomePageController;
import controller.LoginController;
import controller.RegisterController;
import controller.RequestMovieController;
import controller.ShowMoviesController;
import dao.MovieDao;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;


public class Main {


    // https://javalin.io/tutorials/html-forms-example
    public static void main(String[] args) {

        //These two lines are hello world
        //Javalin app = Javalin.create().start(7000);
        //app.get("/", ctx -> ctx.result("Hello World"));

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(7777);


        app.routes(() -> {
//            get("/login", LoginController.loginHandler);
            get("", HomePageController.homepageHandler);
            get("/register", RegisterController.handleRegisterGet);
            get("/showMovies", ShowMoviesController.showMovies);
            post("/pendingPage", ShowMoviesController.showPendingMovies);
            //get("/search",ShowMoviesController.search);

            post("/loginPage", LoginController.loginHandler);
            post("/handleLogin", LoginController.handleLoginPage);
            post("/registerPage", RegisterController.handleRegisterGet);
            post("/showMoviesPage",ShowMoviesController.showMovies);
            post("/searchPage",ShowMoviesController.search);
            post("/handleRegister", RegisterController.handleRegisterPost);
            post("/search", ShowMoviesController.searchResults);
            post("/logOut", LoginController.logOutHandler);
            get("/showMovies/:id",ShowMoviesController.showOneMovie);
            get("/showMoviesPage/:id",ShowMoviesController.showOneMovie);
            get("/movieRequest", RequestMovieController.handleRequestGet);
            post("/handleRequest", RequestMovieController.handleRequestPost);
            post("/acceptFromPending/:id", ShowMoviesController.addMoviePost);
            post("/deleteFromPending/:id", ShowMoviesController.deletePendingMoviePost);
            post("/deleteFromMain/:id", ShowMoviesController.deleteMainMoviePost);
           



//            before(Filters.handleLocaleChange);
//            before(controller.LoginController.ensureLoginBeforeViewingBooks);
//            get(Web.INDEX, IndexController.serveIndexPage);
//            get(Web.BOOKS, BookController.fetchAllBooks);
//            get(Web.ONE_BOOK, BookController.fetchOneBook);
//            get(Web.LOGIN, controller.LoginController.serveLoginPage);
//            post(Web.LOGIN, controller.LoginController.handleLoginPost);
//            post(Web.LOGOUT, controller.LoginController.handleLogoutPost);
        });
//        app.post("/login", ctx -> {
//            ctx.html("You've logged in");
//        });
        
//        app.post("/register", ctx -> {
//        	ctx.html("register.vm");
//        });
    }
}
