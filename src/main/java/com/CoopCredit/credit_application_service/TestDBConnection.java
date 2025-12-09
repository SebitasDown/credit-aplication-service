package com.CoopCredit.credit_application_service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://mysql-cb47f1b-mazoosebas-b7e8.g.aivencloud.com:26554/defaultdb?useSSL=true&requireSSL=true";
        String user = "avnadmin";
        String password = "AVNS_2Qftsx_8VJDBC7AkaRx";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexion exitosa aaaaaaaaaaaa :c");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}