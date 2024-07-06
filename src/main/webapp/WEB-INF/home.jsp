<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 7/6/2024
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bazlur.eshoppers.web.ProductDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>

        All Products

    </title>
</head>
<body>
<% List<ProductDTO> products = (List<ProductDTO>) request.getAttribute("products");%>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
    </tr>
    <% for(ProductDTO product: products){%>
    <tr>
        <td><%=product.getName()%></td>
        <td><%=product.getDescription()%></td>
        <td><%=product.getPrice()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
