package com.example.java_jakarta;

import java.io.*;
import java.sql.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {



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

                 //ЗАПОВНЕННЯ ТАБЛИЦІ
//                   String postgreCommand =  "INSERT INTO Notepades (manufacturer_name, notebook_name, number_of_pages, cover_type,"
//                    +" manufacturer_country, page_layout)\n" +
//                    "VALUES\n" +
//                    "('Фірма1', 'Блокнот 1', 100, 'тверда', 'Україна', 'клітинка'),\n" +
//                    "('Фірма2', 'Блокнот 2', 80, 'м''яка', 'США', 'лінійка'),\n" +
//                    "('Фірма3', 'Блокнот 3', 120, 'тверда', 'Китай', 'порожньо')";


            //             Створення таблиці "NotePad"
//            String postgreCommand =  "CREATE TABLE Notepades (" +
//                    "notebook_id SERIAL PRIMARY KEY," +
//                    "manufacturer_name VARCHAR(255)," +
//                    "notebook_name VARCHAR(255)," +
//                    "number_of_pages INT," +
//                    "cover_type VARCHAR(50)," +
//                    "manufacturer_country VARCHAR(100)," +
//                    "page_layout VARCHAR(50))";

             //SQL COMMAND
           // String sqlCommand = "CREATE TABLE products (Id INT PRIMARY KEY AUTO_INCREMENT, ProductName VARCHAR(20), Price INT)";

//            Statement statement = conn.createStatement();
//            statement.executeUpdate(postgreCommand);
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");
//            out.println("<h1>" + "Data!" + "</h1>");
//            out.println("</body></html>");

          //ALL NOTESPAD
            String postgreCommand = "SELECT * FROM Notepades";

            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(postgreCommand);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");

            while(set.next())
            {
               int id= set.getInt("notebook_id");
               String manuf = set.getString("manufacturer_name");
               String notebook_name = set.getString("notebook_name");
               int number_of_pages = set.getInt("number_of_pages");
               String cover_type = set.getString("cover_type");
               String manufacturer_country = set.getString("manufacturer_country");
               String page_layout = set.getString("manufacturer_country");

                out.println("<p>ID: " + id + "</p>");
                out.println("<p>Назва фірми виробника: " + manuf + "</p>");
                out.println("<p>Назва блокнота: " + notebook_name + "</p>");
                out.println("<p>Кількість сторінок: " + number_of_pages + "</p>");
                out.println("<p>Тип обкладинки: " + cover_type + "</p>");
                out.println("<p>Країна виробник: " + manufacturer_country + "</p>");
                out.println("<p>Зовнішній вигляд сторінки: " + page_layout + "</p>");

            }
            out.println("</body></html>");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
    }

    public void destroy() {
    }
}

