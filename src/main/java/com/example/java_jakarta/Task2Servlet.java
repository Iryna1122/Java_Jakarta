package com.example.java_jakarta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task2Servlet", value = "/task2")
public class Task2Servlet extends HttpServlet {


    private String message;

    public void init() {
        message = "Hello World!";
    }

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
            out.println("<h2>Показати назви країн і кількість блокнотів із кожної країни</h2>");
            //Показати назви країн і кількість блокнотів із кожної країни
            String postgreCommand = "SELECT manufacturer_country, COUNT(*) AS notebook_count FROM Notepades GROUP " +
                    "BY manufacturer_country";
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(postgreCommand);
            response.setContentType("text/html; charset=UTF-8");
            out.println("<html><body>");

            while (set.next()) {
                String manufacturer_country = set.getString("manufacturer_country");
                int notebook_count = set.getInt("notebook_count");

                out.println("<p>Країна виробник: " + manufacturer_country + "-"+ notebook_count+"</p>");
                out.println("<p>Кількість блокнотів: " + notebook_count + "</p>");
                out.println("<br>");
            }
            out.println("</body></html>");
            out.println("<h2>Показати назви all країн</h2>");
            String postgreCommand2 = "SELECT DISTINCT manufacturer_country FROM Notepades";
            Statement statement2 = conn.createStatement();
            ResultSet set2 = statement2.executeQuery(postgreCommand2);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out2 = response.getWriter();
            out2.println("<html><body>");

            while (set2.next()) {
                String manufacturer_country = set2.getString("manufacturer_country");
                out2.println("<p>Країна виробник: " + manufacturer_country + "</p>");
                out2.println("<br>");
            }
            out2.println("</body></html>");

            out.println("<h2>Показати назву виробника і кількість блокнотів кожного виробника</h2>");
            String postgresQuery = "SELECT manufacturer_name, COUNT(*) AS notebook_count FROM Notepades GROUP " +
                    "BY manufacturer_name";

            Statement statement3 = conn.createStatement();
            ResultSet set3 = statement3.executeQuery(postgresQuery);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out3 = response.getWriter();
            out3.println("<html><body>");

            while (set3.next()) {
                String manufacturer_name = set3.getString("manufacturer_name");
                int notebook_count = set3.getInt("notebook_count");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Manufacturer Name</th>");
                out.println("<th>Count Notepad</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + manufacturer_name + "</td>");
                out.println("<td>" + notebook_count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out3.println("<br>");
            }
            out3.println("</body></html>");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void destroy() {
    }
}
