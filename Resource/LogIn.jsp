<!DOCTYPE html>

<html>
<head>
    <title>Log-In</title>
    <meta http-equiv=Content-Type content="text/html">
</head>

<body>

<jsp:useBean id="login" class="Mvc.View.LogInBean" scope="session"/>
<jsp:setProperty name="login" property="*"/>

<form method="post" action="check.jsp">

    <table width="100%">
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit"/></td>
    </table>
</form>
</body>
</html>