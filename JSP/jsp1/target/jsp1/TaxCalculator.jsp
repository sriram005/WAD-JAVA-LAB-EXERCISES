<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Tax Calculator - Result</title>
    </head>
    <body>
        <% 
            String name = request.getParameter("name");
            String pay = request.getParameter("income");
            String maritalStatus = request.getParameter("maritalStatus");
            String age = request.getParameter("age");
            int income = Integer.parseInt(pay);
            double tax = 0.0;
            if (income < 200000) {
                tax = 0.15;
            } else if (income >= 200000 && income < 500000) {
                tax = 0.20;
            } else {
                tax = 0.25;
            }
    
            if (maritalStatus.equals("married")) {
                tax -= 0.05;
            }
    
            if (age.equals("above60")) {
                tax -= 0.05;
            }
    
            double interest =  income * tax;
        %>
        <h1>Tax Calculator - Result</h1>
        <p>Name: <%= name %></p>
        <p>Income: <%= pay %></p>
        <p>Marital Status: <%= maritalStatus %></p>
        <p>Age: <%= age %></p>
        <p>Tax: <%= interest %></p>
    </body>
</html>