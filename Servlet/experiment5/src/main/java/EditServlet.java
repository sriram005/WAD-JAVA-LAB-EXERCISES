import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eId = request.getParameter("eId");
        String eName = request.getParameter("eName");
        String eMail = request.getParameter("eMail");
        String eContact = request.getParameter("eContact");
        PrintWriter pw = response.getWriter();
        boolean isSuccess = UpdateEmp(eId, eName, eMail, eContact);
        if (isSuccess) {
            response.sendRedirect("Display");
        } else {
            pw.println("Somthing went wrong");
        }
    }

    private boolean UpdateEmp(String eId, String eName, String eMail, String eContact) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "Update employee set  eName = ?, eMail = ?, eContact = ? where eId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, eName);
            pstmt.setString(2, eMail);
            pstmt.setString(3, eContact);
            pstmt.setString(4, eId);
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