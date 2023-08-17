package com.example.java_jakarta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "task5Servlet", value = "/task5")
public class Task5Servlet extends HttpServlet {

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
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");
            out.println("<h3>Додавання нового блокнота</h3>");
            out.println("<form action='task5' method='post'>");
            out.println("Назва фірми виробника: <input type='text' name='manufacturer'><br>");
            out.println("Назва блокнота: <input type='text' name='notebook'><br>");
            out.println("Count of pages: <input type='number' name='number_of_pages'><br>");
            out.println("Type: <input type='text' name='type'><br>");
            out.println("Country: <input type='text' name='country'><br>");
            out.println("Page Layout: <input type='text' name='layout'><br>");

            out.println("<input type='submit' value='Додати'>");
            out.println("</form>");
            out.println("</html></body>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/NotePad";
        String username = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String manuf = request.getParameter("manufacturer");
            String name = request.getParameter("notebook");
            int count = Integer.parseInt(request.getParameter("number_of_pages"));
            String type = request.getParameter("type");
            String country = request.getParameter("country");
            String layout = request.getParameter("layout");

            String insertCommand = "INSERT INTO Notepades (manufacturer_name, notebook_name, number_of_pages, cover_type, manufacturer_country, page_layout) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = conn.prepareStatement(insertCommand);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                preparedStatement.setString(1, manuf);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, count);
                preparedStatement.setString(4, type);
                preparedStatement.setString(5, country);
                preparedStatement.setString(6, layout);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
