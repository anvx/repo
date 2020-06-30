<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Online Shop</title>
</head>

<style>
    .error {
        padding: 15px;
        margin-bottom: 20px;
        border: 1px solid transparent;
        border-radius: 4px;
        color: #a94442;
        background-color: #f2dede;
        border-color: #ebccd1;
    }

    .msg {
        padding: 15px;
        margin-bottom: 20px;
        border: 1px solid transparent;
        border-radius: 4px;
        color: #31708f;
        background-color: #d9edf7;
        border-color: #bce8f1;
    }

    #login-box {
        width: 300px;
        padding: 20px;
        margin: 50px auto;
        background: #fff;
        -webkit-border-radius: 2px;
        -moz-border-radius: 2px;
        border: 1px solid #000;
    }
</style>

<script>
    function agreeForm(f) {
        // Если поставлен флажок, снимаем блокирование кнопки
        if (f.agree.checked) f.submit.disabled = 0
        // В противном случае вновь блокируем кнопку
        else f.submit.disabled = 1
    }
</script>

<body>
<h1 align="center">Welcome to Online Shop</h1>
<br/>
<%
    if (request.getAttribute("error") != null) {
%>
<div class ="error" align="center"> ${error} </div>
</br>
<%
    }
%>

<%
    if (request.getAttribute("logout") != null) {
%>
<div class ="msg" align="center"> ${logout} </div>
</br>
<%
    }
%>

<div id="login-box">
    <form name='loginpage' action="login" method='POST'>
        <table align="center">
            <tr>
                <td>User: <input type='text' name='username' value='' align="center"></td>
            </tr>
            <tr>
                <td>Password: <input type='password' name='password'/></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="submit" align="center" disabled/></td>
            </tr>
            <tr>
                <td>
                    <input type="checkbox" name="agree" onclick="agreeForm(this.form)">
                    I agree with the terms of service
                </td>
            </tr>

        </table>
    </form>
</div>
</body>

</html>
