<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">
<head>
    <title>Spring Load Time Weaving Clickstart</title>
</head>
<body>
<h1>Spring Load Time Weaving Clickstart</h1>

<ul>
    <li><a href="${pageContext.request.contextPath}/products">Products</a> (Relies on <code>@Configurable</code>
        entities with <code>VatService</code> injected at load time)
    </li>
</ul>

</body>

</html>