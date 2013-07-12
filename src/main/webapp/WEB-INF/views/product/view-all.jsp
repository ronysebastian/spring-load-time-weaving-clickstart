<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html lang="en">
<head>
    <title>Products</title>
</head>
<body>
<h1>Products</h1>
Relies on <code>@Configurable</code>
<code>Product</code> entities with a <code>VatService</code> service injected at load time to calculate the VAT and the
Gross Price.
<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Net Price</th>
        <th>VAT</th>
        <th>Gross Price</th>
    </tr>
    </thead>
    <c:forEach items="${products.productList}" var="product">
        <tr>
            <td><strong>${product.name}</strong></td>
            <td>${product.netPriceAsText}</td>
            <td>${product.vatAsText}</td>
            <td>${product.grossPriceAsText}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>