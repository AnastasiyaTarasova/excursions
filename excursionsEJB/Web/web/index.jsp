<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 14.05.2016
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>${title}</title>
  </head>
  <body>
    <h1>${title}</h1>
    ${message}
    <h2>Add excursion</h2>
    <form method="POST">
      <input type="text" name="name">
      <input type="submit">
    </form>
  </body>
</html>
