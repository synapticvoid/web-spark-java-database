package org.example;

import org.example.core.Conf;
import org.example.core.Database;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.user.AuthController;
import spark.Session;
import spark.Spark;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        initialize();

        final AuthController authController = new AuthController();

        // Login
        Spark.get(Conf.ROUTE_LOGIN, (req, res) -> authController.login(req, res));
        Spark.post(Conf.ROUTE_LOGIN, (req, res) -> authController.login(req, res));


        // Default
        Spark.get("/", (req, res) -> {
            Session session = req.session(false);
            if (session == null) {
                res.redirect(Conf.ROUTE_LOGIN);
            } else {
                res.redirect(Conf.ROUTE_AUTHENTICATED_ROOT);
            }
            return null;
        });
    }

    static void initialize() {
        Template.initialize();
        Database.get().checkConnection();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
