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
        <%@ include file="/js/guide.js" %>
    </script>
</head>
<body>
<h1>Create your excursions</h1>

<div>
    <h2>New excursion</h2>
    <div id="new-excursion">
        Name: <input type="text" name="Name"/><br/>
        Description: <input type="text" name="Description"/><br/>
        Price: <input type="number" name="Price"/><br/>
        <input id="create-excursion" type="button" value="Create excursion"/>
    </div>
    <h2>Excursions</h2>
    <table id="excursions">
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>People</th>
        </tr>
    </table>
</div>

</body>
</html>
