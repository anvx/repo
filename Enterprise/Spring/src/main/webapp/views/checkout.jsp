<%@ page import="java.util.List" %>
<%@ page import="com.jtmog.alukyanov.entity.Good" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Checkout</title>
</head>

<body>
<h1 align="center">Dear ${currentUser.login} your order:</h1>
<div align="center">

    <%
        List<Good> listOfGoods = (List<Good>) request.getAttribute("bucket");
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
            <%= good.getTitle() %>
            <%= good.getPrice() %> $
        </li>
        <%
            }
        %>
    </ol>
    <br>
    <div align="center">
        Total: <%= request.getAttribute("totalPrice")%>$
    </div>

</div>
<br>
<br>
<br>
<div align="center">
    <a href="/logout">Logout</a>
</div>
</body>

</html>
