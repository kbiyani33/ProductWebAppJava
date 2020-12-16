<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Display Page</title>
</head>
<body>
        <h2>Product List</h2>
        Welcome, ${user}
        <table border="1">
                <tr>
                        <th>ID</th><th>Name</th><th>Price</th>
                </tr>
                <c:forEach items="${products}" var="p">
                        <tr>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td>${p.price}</td>
                        </tr>
                </c:forEach>
                <tr>
                <td>Total</td>
                <td colspan="2">${total}</td>
                </tr>
        </table>
        <a href="logout.do">Logout</a>
</body>
</html>