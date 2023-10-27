import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("Welcome, "+session.getAttribute("studentId")+"");
    }
}