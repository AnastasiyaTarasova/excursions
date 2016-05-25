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
        <%@ include file="/js/admin.js" %>
    </script>
</head>
<body>
    <h1>Administration</h1>

    <div>
        <h2>New user</h2>
        <div id="new-user">
            UserName: <input type="text" name="UserName"/><br/>
            First Name: <input type="text" name="FirstName"/><br/>
            Last Name: <input type="text" name="LastName"/><br/>
            Password: <input type="text" name="Password"/><br/>
            Role: <select name="UserType">
                    <option value="0">Admin</option>
                    <option value="1">Guide</option>
                    <option value="2">Client</option>
                  </select>
            <br/>
            <input id="create-user" type="button" value="Create user"/>
        </div>
        <h2>Users</h2>
        <table id="users">
            <tr>
                <th>UserName</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Role</th>
            </tr>
        </table>
    </div>

</body>
</html>
