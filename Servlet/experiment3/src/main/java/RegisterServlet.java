
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        if (newPassword.equals(confirmPassword)) {
            boolean registrationSuccess = registerUser(newUsername, newPassword);

            if (registrationSuccess) {
                response.sendRedirect("index.html");
            } else {
                response.getWriter().println("Registration failed. Please try again.");
            }
        } else {
            response.getWriter().println("Passwords do not match. Please try again.");
        }
    }

    private boolean registerUser(String newUsername, String newPassword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newPassword);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

// create table
// create table users(id int primary key auto_increment, username varchar(20), password varchar(20));