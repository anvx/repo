<%@ page import="com.jtmog.alukyanov.entity.Good" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Order page</title>
</head>

<body>
<h1 align="center">Hello
    <%= session.getAttribute("user") %>
    !</h1>
<br>
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

    <br>
    <br>
    Make your order
</div>

<div align="center">
    <form method="post">
        <label>
            <select name="orderGood">
                <%
                    List<Good> goodFromDB = (List<Good>) request.getAttribute("goodFromDB");
                    for (Good good : goodFromDB) {
                %>
                <option value="<%= good.getName() %>">
                    <%= good.getName() + " " + good.getPrice() + "$"%>
                </option>
                <%
                    }
                %>
            </select>
        </label>
        <button type="submit">Add to order</button>
    </form>

    <form action='checkout' method='get' align="center">
        <button>Checkout</button>
    </form>
</div>
</body>

</html>
