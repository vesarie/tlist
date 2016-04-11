<%@tag description="Page template" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="pageTitle" required="true"%>
<%@attribute name="projects" type="java.util.List" required="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${pageTitle}</title>

    <link href="css/jquery-ui.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="css/tlist.css" rel="stylesheet">
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">tList</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Add task</a></li>
                    <li><a href="#">Settings</a></li>
                    <li><a href="#">Help</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
                <ul class="nav nav-sidebar">
                    <li><a href="#">Today</a></li>
                    <li><a href="#">Next 7 days</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <c:forEach var="p" items="${projects}">
                        <c:choose>
                            <c:when test="${p.id == project.id}">
                                <li class="active"><a href="project?id=${p.id}">${p.name} <span class="sr-only">(current)</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="project?id=${p.id}">${p.name}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
                <!--
                <ul class="nav nav-sidebar">
                    <li><a href="#">#work</a></li>
                    <li><a href="#">#school</a></li>
                    <li><a href="#">#home</a></li>
                </ul>
                <ul class="nav nav-sidebar">
                    <li><a href="#">Priority 1</a></li>
                    <li><a href="#">Priority 2</a></li>
                    <li><a href="#">Priority 3</a></li>
                    <li><a href="#">Priority 4</a></li>
                </ul>
                -->
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <jsp:doBody/>
            </div>
        </div>
    </div>

    <script src="js/jquery-1.12.2.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/ie10-viewport-bug-workaround.js"></script>
    <script src="js/tlist.js"></script>

</body>
</html>
