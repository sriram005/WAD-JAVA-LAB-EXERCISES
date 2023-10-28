import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String eId = request.getParameter("id");
        boolean isSuccess = DeleteEmp(eId);
        if (isSuccess) {
            response.sendRedirect("Display");
        } else {
            pw.println("Somthing went wrong");
        }
    }

    private boolean DeleteEmp(String eId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "Delete from employee where eId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, eId);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            return false;
        }
    }
}