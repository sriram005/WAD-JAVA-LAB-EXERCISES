import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Display")
public class Display extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "p21157";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
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
        pw.println("<div id=\"table\">");
        pw.println("<table border=\"1\">");
        pw.println("<tr>");
        pw.println("<th>Employee ID</th>");
        pw.println("<th>Employee Name</th>");
        pw.println("<th>Employee Email</th>");
        pw.println("<th>Employee Contact</th>");
        pw.println("<th>Actions</th>");
        pw.println("</tr>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "select * from employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getString(1) + "</td>");
                pw.println("<td>" + rs.getString(2) + "</td>");
                pw.println("<td>" + rs.getString(3) + "</td>");
                pw.println("<td>" + rs.getString(4) + "</td>");
                pw.println(
                        "<td>" + "<a href='Edit?id=" + rs.getString("eId") + " '>Edit </a>" + "<a href='Delete?id="
                                + rs.getString("eId") + " '>Delete </a>" + "</td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("<div class=\"btn\">");
            pw.println("  <button class=\"btn-primary\" onclick=\"enterdetails()\">");
            pw.println("    Add New Record");
            pw.println("  </button>");
            pw.println("</div>");
            pw.println("</div>");
            pw.println("<div class='update' id='submit'>");
            pw.println("  <div class='Update-msg'>");
            pw.println("    <h2 id='detail'>Enter Details</h2>");
            pw.println("  </div>");
            pw.println("  <form action='EmployeeServlet' method='post'>");
            pw.println("    <table>");
            pw.println("      <div class='Update-feilds'>");
            pw.println("        <tr>");
            pw.println("          <td>Employee Id:</td>");
            pw.println("          <td><input type='text' name='eId' /></td>");
            pw.println("        </tr>");
            pw.println("        <tr>");
            pw.println("          <td>Employee Name:</td>");
            pw.println("          <td><input type='text' name='eName' /></td>");
            pw.println("        </tr>");
            pw.println("        <tr>");
            pw.println("          <td>Employee Email:</td>");
            pw.println("          <td><input type='email' name='eMail' /></td>");
            pw.println("        </tr>");
            pw.println("        <tr>");
            pw.println("          <td>Employee Contact:</td>");
            pw.println("          <td><input type='text' name='eContact' /></td>");
            pw.println("        </tr>");
            pw.println("      </div>");
            pw.println("    </table>");
            pw.println("    <div class='btn'>");
            pw.println("      <input type='submit' class='btn-primary' onclick='displaytable()' value='Submit' />");
            pw.println("    </div>");
            pw.println("  </form>");
            pw.println("</div>");
            pw.println("<script>");
            pw.println("console.log('Hello')");
            pw.println("  var table = document.getElementById('table');");
            pw.println("  var submit = document.getElementById('submit');");
            pw.println("  submit.style.display = 'none';");
            pw.println("  function enterdetails() {");
            pw.println("    table.style.display = 'none';");
            pw.println("    submit.style.display = 'flex';");
            pw.println("  }");
            pw.println("  function displaytable() {");
            pw.println("    submit.style.display = 'none';");
            pw.println("    table.style.display = 'flex';");
            pw.println("  }");
            pw.println("</script>");
            rs.close();
            stmt.close();
            conn.close();
            pw.println("</body>");
            pw.println("</html>");
        } catch (Exception e) {
            pw.println("Somthing went wrong - Connection");
        }
    }
}