package org.example.core;

import java.io.File;

/**
 * This class centralizes all the configuration of the app (port, database, resources, etc)
 */
public class Conf {
    // General
    public static final boolean DEBUG_MODE = true;
    public static final int HTTP_PORT = 8080;
    // Database
    public static final String DB_HOST = getEnv("DB_HOST", "localhost:3306");
    public static final String DB_NAME = getEnv("DB_NAME", "root");
    public static final String DB_URL = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?serverTimezone=Europe/Paris";
    public static final String DB_USER = getEnv("DB_USER", "root");
    public static final String DB_PASSWORD = getEnv("DB_PASSWORD", "root");

    // Resources
    public static final File RESOURCES_DIR = new File("src/main/resources");
    public static final File TEMPLATE_DIR = new File(RESOURCES_DIR, "templates");
    public static final File STATIC_DIR = new File(RESOURCES_DIR, "static");

    // Routes
    public static final String ROUTE_LOGIN = "/login";
    public static final String ROUTE_AUTHENTICATED_ROOT = "/friends/";

    /**
     * Helper to retrieve an environment variable and use a default value if not found.
     *
     * @param key: name of the environment variable
     * @param def: default value if the key was not found or is empty
     * @return the value at key or default.
     */
    private static String getEnv(String key, String def) {
        String value = System.getenv(key);
        if (value == null || value.isEmpty()) {
            value = def;
        }
        return value;
    }
}
