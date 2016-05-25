<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Admin</title>
    <script>
        <%@ include file="/js/jquery-2.2.3.min.js" %>
    </script>
    <script>
        <%@ include file="/js/services.js" %>
    </script>
    <script>
        <%@ include file="/js/user.js" %>
    </script>
</head>
<body>
<h1>Order your excursions</h1>

<div>
    <h2>Excursions</h2>
    <table id="excursions">
        <tr>
            <th>Guide</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Order</th>
        </tr>
    </table>
</div>

</body>
</html>
