<%--
Created by IntelliJ IDEA.
User: 郑先生
Date: 2022/2/13
Time: 22:12
To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form id="from1" action="Login" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="登陆"/></td>
        </tr>
    </table>
</form>
</body>
</html>
