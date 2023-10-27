<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <h2>Welcome, <%= session.getAttribute("name") %>!</h2>
        <p>Your Student ID: <%= session.getAttribute("studentId") %>
        </p>
        <p>Your Date of Birth: <%= session.getAttribute("dob") %>
        </p>
    </body>
</html>