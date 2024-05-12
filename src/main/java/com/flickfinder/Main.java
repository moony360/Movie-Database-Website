package com.flickfinder;

import com.flickfinder.util.Database;

public class Main {

    static int port = 8000;

    public static void main(String[] args) {

        final String dbPath = "src/main/resources/movies.db";

        /**
         * This sets up the database connection and starts the server.
         * In this case, we are using a connection string to connect to the database.
         * For testing, we are using an in-memory database.
         */
        Database.getInstance("jdbc:sqlite:" + dbPath);
        // start the server
        AppConfig.startServer(port);
    }
}