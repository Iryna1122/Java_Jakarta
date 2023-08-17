package com.example.java_jakarta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task4Servlet", value = "/task4")
public class Task4Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String url = "jdbc:postgresql://localhost:5432/NotePad";
        String username = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PrintWriter out = response.getWriter();

            out.println("<h3>1. Показати всі блокноти конкретної країни</h3>");
            String postgreCommand = "SELECT notebook_name, manufacturer_country FROM Notepades WHERE manufacturer_country='Україна'";

            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(postgreCommand);
            response.setContentType("text/html; charset=UTF-8");
            out.println("<html><body>");
            while(set.next())
            {
                String name = set.getString("notebook_name");
                String country = set.getString("manufacturer_country");

                out.println("<p>"+name+ " ("+ country+")</p>");

            }

            out.println("</html></body>");

            out.println("<h3>2. Створити фільтр для відображення блокнотів по зовнішньому вигляді сторінки</h3>");
            String postgreCommand2 = "SELECT notebook_name, cover_type FROM Notepades";

            Statement statement2 = conn.createStatement();
            ResultSet set2 = statement2.executeQuery(postgreCommand2);
            response.setContentType("text/html; charset=UTF-8");
            out.println("<html><body>");
            while(set2.next())
            {
                String name = set2.getString("notebook_name");
                String type = set2.getString("cover_type");

                out.println("<p>"+name+ " ("+ type+")</p>");

            }

            out.println("</html></body>");


            out.println("<h3>3. Створити фільтр по кількості сторінок</h3>");
            String postgreCommand3 = "SELECT notebook_name, number_of_pages FROM Notepades";

            Statement statement3 = conn.createStatement();
            ResultSet set3 = statement3.executeQuery(postgreCommand3);
            response.setContentType("text/html; charset=UTF-8");
            out.println("<html><body>");
            while(set3.next())
            {
                String name = set3.getString("notebook_name");
                int num = set3.getInt("number_of_pages");

                out.println("<p>"+name+ " ("+ num+")</p>");

            }

            out.println("</html></body>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }
