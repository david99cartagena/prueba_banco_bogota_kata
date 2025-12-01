/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kata.middle.data.engineer.repository;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DavidPC
 */
public class Database {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    private static final String HOST = dotenv.get("PG_HOST", "localhost");
    private static final String DB = dotenv.get("PG_DB", "postgres");
    private static final String USER = dotenv.get("PG_USER", "postgres");
    private static final String PASS = dotenv.get("PG_PASSWORD", "");

    private static final String URL = "jdbc:postgresql://" + HOST + ":5432/" + DB;

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
