import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isValidUser = checkCredentials(username, password);
        if (isValidUser) {
            response.sendRedirect("home.html");
        } else {
            response.getWriter().println("Invalid credentials. Please try again.");
        }
    }

    private boolean checkCredentials(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            boolean isValid = resultSet.next();
            resultSet.close();
            pstmt.close();
            conn.close();
            return isValid;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
