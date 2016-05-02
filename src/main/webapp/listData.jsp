<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>List of Data</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container-fluid" style="max-width: 970px">
    <h2>Person</h2>

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>id</th>
                <th>forename</th>
                <th>surname</th>
                <th>email</th>
            </tr>
        </thead>
        <tbody>   
            <c:forEach var="person" items="${persons}">
            <tr>
                <td><c:out value="${person.id}"/></td>
                <td><c:out value="${person.forename}"/></td>
                <td><c:out value="${person.surname}"/></td>
                <td><c:out value="${person.email}"/></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Project</h2>

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>id</th>
                <th>person</th>
                <th>name</th>
            </tr>
        </thead>
        <tbody>   
            <c:forEach var="project" items="${projects}">
            <tr>
                <td><c:out value="${project.id}"/></td>
                <td><c:out value="${project.person}"/></td>
                <td><c:out value="${project.name}"/></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Task</h2>

    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>id</th>
                <th>project</th>
                <th>name</th>
                <th>priority</th>
                <th>schedule</th>
                <th>completed</th>
            </tr>
        </thead>
        <tbody>   
            <c:forEach var="task" items="${tasks}">
            <tr>
                <td><c:out value="${task.id}"/></td>
                <td><c:out value="${task.project}"/></td>
                <td><c:out value="${task.name}"/></td>
                <td><c:out value="${task.priority}"/></td>
                <td><c:out value="${task.schedule}"/></td>
                <td><c:out value="${task.completed}"/></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
