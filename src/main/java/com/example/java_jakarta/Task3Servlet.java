package com.example.java_jakarta;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "task3Servlet", value = "/task3")
public class Task3Servlet extends HttpServlet {

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

            // FILL TABLE
//            String postgreCommand ="INSERT INTO Notepades (manufacturer_name, notebook_name, number_of_pages, cover_type,"+
//                    " manufacturer_country, page_layout)" +
//                    "VALUES" +
//                    "('Company1', 'Notepad 1', 100, 'тверда', 'Україна', 'клітинка')," +
//                    "('Company2', 'Notepad 2', 80, 'м''яка', 'Україна', 'лінійка')," +
//                    "('Company2', 'Notepad 3', 120, 'тверда', 'Китай', 'порожньо')," +
//                    "('Company3', 'Notepad 4', 120, 'тверда', 'Китай', 'порожньо')," +
//                    "('Company3', 'Notepad 5', 120, 'тверда', 'Іспанія', 'порожньо'),"+
//                    "('Company1', 'Notepad 5', 120, 'тверда', 'Китай', 'порожньо'),"+
//                    "('Company1', 'Notepad 5', 120, 'тверда', 'Китай', 'порожньо')";
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(postgreCommand);
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<html><body>");

            out.println("<h3>1. Показати країну з найбільшою кількістю блокнотів</h3>");
            String postgreCommand = "SELECT manufacturer_country, COUNT(*) AS countN " +
                    "FROM Notepades GROUP BY manufacturer_country " +
                    "ORDER BY countN DESC LIMIT 1";

            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(postgreCommand);
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");

            if (set.next()) {
                String man_country = set.getString("manufacturer_country");
                int not_count = set.getInt("countN");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th> Країна з найбільшою кількістю блокнотів:</th>");
                out.println("<th>Count Notepad</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + man_country + "</td>");
                out.println("<td>" + not_count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            } else {
                out.println("<p>Немає даних про блокноти</p>");
            }
            out.println("</body></html>");
            out.println("<h3>2.Показати країну з найменшою кількістю блокнотів</h3>");

            String postgreCommand2 = "SELECT manufacturer_country, COUNT(*) AS countN " +
                    "FROM Notepades GROUP BY manufacturer_country " +
                    "ORDER BY countN ASC LIMIT 1";

            Statement statement2 = conn.createStatement();
            ResultSet set2 = statement2.executeQuery(postgreCommand2);
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");

            if (set2.next()) {
                String man_country = set2.getString("manufacturer_country");
                int not_count = set2.getInt("countN");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Країна з найменшою кількістю блокнотів:</th>");
                out.println("<th>Count Notepad</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + man_country + "</td>");
                out.println("<td>" + not_count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            } else {
                out.println("<p>Немає даних про блокноти</p>");
            }
            out.println("</body></html>");

            out.println("<h3>3. Показати виробника з найбільшою кількістю блокнотів</h3>");

            String postgreCommand3 = "SELECT manufacturer_name, COUNT(*) AS countN " +
                    "FROM Notepades GROUP BY manufacturer_name " +
                    "ORDER BY countN DESC LIMIT 1";

            Statement statement3 = conn.createStatement();
            ResultSet set3 = statement3.executeQuery(postgreCommand3);
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");

            if (set3.next()) {
                String man_country = set3.getString("manufacturer_name");
                int not_count = set3.getInt("countN");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Країна з найменшою кількістю блокнотів:</th>");
                out.println("<th>Count Notepad</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + man_country + "</td>");
                out.println("<td>" + not_count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            } else {
                out.println("<p>Немає даних про блокноти</p>");
            }
            out.println("</body></html>");

            //--------------------
            out.println("<h3>4. Показати виробника з найменшою кількістю блокнотів</h3>");

            String postgreCommand4 = "SELECT manufacturer_name, COUNT(*) AS countN " +
                    "FROM Notepades GROUP BY manufacturer_name " +
                    "ORDER BY countN ASC LIMIT 1";

            Statement statement4 = conn.createStatement();
            ResultSet set4 = statement4.executeQuery(postgreCommand4);
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");

            if (set4.next()) {
                String man_country = set4.getString("manufacturer_name");
                int not_count = set4.getInt("countN");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Виробник з найменшою кількістю блокнотів:</th>");
                out.println("<th>Count Notepad</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + man_country + "</td>");
                out.println("<td>" + not_count + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            } else {
                out.println("<p>Немає даних про блокноти</p>");
            }
            out.println("</body></html>");


            //------------------------

            out.println("<h3>5. Показати всі блокноти з твердою обкладинкою</h3>");

            String postgreCommand5 = "SELECT notebook_name " +
                    "FROM Notepades WHERE cover_type ='тверда' ";

            Statement statement5 = conn.createStatement();
            ResultSet set5 = statement5.executeQuery(postgreCommand5);
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");

            while (set5.next()) {
                String name = set5.getString("notebook_name");

                out.println("<p>блокнот з твердою обкладинкою: "+ name +" </p>");


            }
            out.println("</body></html>");

            out.println("<h3>6. Показати всі блокноти з м'якою обкладинкою</h3>");

            String postgreCommand6 = "SELECT notebook_name " +
                    "FROM Notepades WHERE cover_type ='м''яка' ";

            Statement statement6 = conn.createStatement();
            ResultSet set6 = statement6.executeQuery(postgreCommand6);
            response.setContentType("text/html; charset=UTF-8");

            out.println("<html><body>");

            while (set6.next()) {
                String name = set6.getString("notebook_name");

                out.println("<p>блокнот з м'якою обкладинкою: "+ name +" </p>");


            }
            out.println("</body></html>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void destroy() {
    }
}
