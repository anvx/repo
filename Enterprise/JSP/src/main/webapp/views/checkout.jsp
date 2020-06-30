<%@ page import="com.jtmog.alukyanov.entity.User" %>
<%@ page import="com.jtmog.alukyanov.model.Model" %>
<%@ page import="java.util.List" %>
<%@ page import="com.jtmog.alukyanov.entity.Good" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jtmog.alukyanov.model.ModelGood" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Checkout</title>
</head>

<body>
<h1 align="center">Dear <%= session.getAttribute("user") %> your order:</h1>
<div align="center">

    <%
        List<Good> listOfGoods = (List<Good>) getServletConfig().getServletContext().getAttribute("listOfGoods");
        if (listOfGoods.isEmpty()) {
    %>
    <h4>You bucket is empty.</h4>
    <%
        }
    %>

    <ol>
        <%
            for (Good good : listOfGoods) {
        %>
        <li>
            <%= good.getName() %>
            <%= good.getPrice() %> $
        </li>
        <%
            }
        %>
    </ol>

</div>
<div align="center">
    Total: <%= getServletConfig().getServletContext().getAttribute("amount")%>$
</div>
</body>

</html>
