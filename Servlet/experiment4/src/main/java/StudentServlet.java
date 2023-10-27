import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        HttpSession session = request.getSession();
        session.setAttribute("studentId", studentId);
        session.setAttribute("name", name);
        session.setAttribute("dob", dob);
        Cookie studentIdCookie = new Cookie("studentId", studentId);
        Cookie nameCookie = new Cookie("name", name);
        Cookie dobCookie = new Cookie("dob", dob);
        studentIdCookie.setMaxAge(3600);
        nameCookie.setMaxAge(3600);
        dobCookie.setMaxAge(3600);
        response.addCookie(studentIdCookie);
        response.addCookie(nameCookie);
        response.addCookie(dobCookie);
        response.sendRedirect("WelcomeServlet");
    }
}
