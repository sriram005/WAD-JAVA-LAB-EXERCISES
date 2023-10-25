import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String changeUsername = request.getParameter("changeUsername");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        if (newPassword.equals(confirmNewPassword)) {
            boolean passwordChangeSuccess = changePassword(changeUsername, oldPassword, newPassword);
            if (passwordChangeSuccess) {
                response.sendRedirect("index.html");
            } else {
                response.getWriter().println("Password change failed. Please try again.");
            }
        } else {
            response.getWriter().println("New passwords do not match. Please try again.");
        }
    }

    private boolean changePassword(String changeUsername, String oldPassword, String newPassword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "UPDATE users SET password = ? WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, changeUsername);
            pstmt.setString(3, oldPassword);
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