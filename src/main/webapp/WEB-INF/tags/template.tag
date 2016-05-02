<%@tag description="Page template" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="page" required="true"%>
<%@attribute name="pageTitle" required="true"%>
<%@attribute name="projects" type="java.util.List" required="true"%>
<t:base pageTitle="${pageTitle}">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed"
                        data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">tList</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <!--<li><a href="#">Add task</a></li>-->
                    <li>
                        <a href="#">
                            <span class="glyphicon glyphicon-cog"></span> Settings
                        </a>
                    </li>
                    <li>
                        <form class="navbar-form" name="logoutForm" method="post" action="logout">
                            <button type="submit" class="btn btn-link">
                                <span class="glyphicon glyphicon-log-out"></span> Log out
                            </button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <c:set var="marker"><span class="sr-only">(current)</span></c:set>

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
                            <c:when test="${page == 'project' && p.id == project.id}">
                                <li class="active">
                                    <a href="project?id=${p.id}"><c:out value="${p.name}"/> ${marker}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="project?id=${p.id}"><c:out value="${p.name}"/></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${page == 'projects'}">
                            <li class="active"><a href="projects">Edit projects ${marker}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="projects">Edit projects</a></li>
                        </c:otherwise>
                    </c:choose>
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
</t:base>