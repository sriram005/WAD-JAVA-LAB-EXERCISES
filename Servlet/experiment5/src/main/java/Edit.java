import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Edit")
public class Edit extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String eId = request.getParameter("id");
        pw.println("<html>");
        pw.println("<head><title>Employee Details</title></head>");
        pw.println("<style>");
        pw.println("  th {");
        pw.println("    background-color: black;");
        pw.println("    color: white;");
        pw.println("  }");
        pw.println("  table {");
        pw.println("    width: 100%;");
        pw.println("  }");
        pw.println("  p, .Update-msg, .update {");
        pw.println("    flex-direction: column;");
        pw.println("    display: flex;");
        pw.println("    align-items: center;");
        pw.println("    justify-content: center;");
        pw.println("  }");
        pw.println("  .btn {");
        pw.println("    display: flex;");
        pw.println("    align-items: center;");
        pw.println("    justify-content: center;");
        pw.println("    margin: 25px;");
        pw.println("  }");
        pw.println("  .btn-primary {");
        pw.println("    color: white;");
        pw.println("    background-color: rgb(13, 121, 236);");
        pw.println("    padding: 10px;");
        pw.println("    border-width: 0px;");
        pw.println("    border-radius: 6px;");
        pw.println("  }");
        pw.println("  .btn-success {");
        pw.println("    background-color: rgb(6, 196, 6);");
        pw.println("    color: white;");
        pw.println("    padding: 10px;");
        pw.println("    border-width: 0px;");
        pw.println("    border-radius: 6px;");
        pw.println("  }");
        pw.println("  .update td {");
        pw.println("    padding: 8px;");
        pw.println("    font-size: large;");
        pw.println("  }");
        pw.println("</style>");
        pw.println("<body>");
        pw.println("<div class='update' id='update'>");
        pw.println("  <div class='Update-msg'>");
        pw.println("    <h2 id='detail'>Update Details</h2>");
        pw.println("  </div>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(" select * from employee where eId = ?");
            stmt.setString(1, eId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pw.println("<form action='EditServlet' method='post'>");
                pw.println("<table>");
                pw.println("<div class='Update-feilds'>");
                pw.println("<tr>");
                pw.println("<td>Employee Id:</td>");
                pw.println("<td><input type='text' name='eId' value = " + rs.getString(1) + "></td>");
                pw.println("</tr>");
                pw.println("        <tr>");
                pw.println("          <td>Employee Name:</td>");
                pw.println("          <td><input type='text' name=\"eName\" value=" + rs.getString(2) + "></td>");
                pw.println("        </tr>");
                pw.println("        <tr>");
                pw.println("          <td>Employee Email:</td>");
                pw.println("          <td><input type='email' name=\"eMail\"  value=" + rs.getString(3) + " ></td>");
                pw.println("        </tr>");
                pw.println("        <tr>");
                pw.println("          <td>Employee Contact:</td>");
                pw.println("          <td><input type='text' name='eContact' value=" + rs.getString(4) + "></td>");
                pw.println("        </tr>");
                pw.println("      </div>");
                pw.println("    </table>");
                pw.println("    <div class='btn'>");
                pw.println(
                        "      <input type='submit' class='btn-success' value='Update'></input>");
                pw.println("    </div>");
                pw.println("  </form>");
            }
            stmt.close();
            conn.close();
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        } catch (Exception e) {
            pw.println("Somthing went wrong");
        }
    }
}