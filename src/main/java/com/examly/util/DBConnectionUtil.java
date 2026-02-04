package com.examly.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_PORT = "3306";
    private static final String DEFAULT_DB = "appdb";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "kani@2004";

    private DBConnectionUtil() {
    }

    public static Connection getConnection() throws SQLException {
        String host = getSetting("PETFINDR_DB_HOST", DEFAULT_HOST);
        String port = getSetting("PETFINDR_DB_PORT", DEFAULT_PORT);
        String dbName = getSetting("PETFINDR_DB_NAME", DEFAULT_DB);
        String username = getSetting("PETFINDR_DB_USER", DEFAULT_USERNAME);
        String password = getSetting("PETFINDR_DB_PASS", DEFAULT_PASSWORD);

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, username, password);
    }

    private static String getSetting(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null && !value.trim().isEmpty()) {
            return value.trim();
        }

        value = System.getenv(key);
        if (value != null && !value.trim().isEmpty()) {
            return value.trim();
        }

        return defaultValue;
    }

    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception ignored) {
        }
    }
}
