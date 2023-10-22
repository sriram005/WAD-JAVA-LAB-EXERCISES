import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

@WebServlet("/FormData")
public class FormData extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String name = req.getParameter("fname");
        String gender = req.getParameter("gender");
        String mail = req.getParameter("email");
        String dob = req.getParameter("dob");
        String deg = req.getParameter("degree");
        String branch = req.getParameter("branch");
        String phone = req.getParameter("phone");
        String[] skills = req.getParameterValues("skill");
        pw.println("<html>");
        pw.println("<head><title>Form Data</title></head>");
        pw.println("<body>");
        pw.println("<h2>Personal Info</h2>");
        pw.println("<p>Name: " + name + "</p>");
        pw.println("<p>Gender: " + gender + "</p>");
        pw.println("<p>Mail: " + mail + "</p>");
        pw.println("<p>Date of Birth: " + dob + "</p>");
        pw.println("<p>Course: " + deg + " " + branch + "</p>");
        pw.println("<p>Phone Number: " + phone + "</p>");
        pw.println("Technical Skills: ");
        for (String skill : skills) {
            pw.println(skill + ", ");
        }
        pw.println("</p>");
        pw.println("</body>");
        pw.println("</html>");
    }
}
