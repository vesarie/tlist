<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template page="project" pageTitle="${project.name}" projects="${projects}">
    <h2 class="page-header">${project.name}</h2>

    <c:set var="currentPage" value="project?id=${project.id}"/>

    <t:ul-task-list tasks="${tasks}" projectMap="${projectMap}"/>

    <button type="button" class="btn btn-default"
            data-toggle="modal" data-target="#createTaskModal">Create a task</button>

    <div class="pull-right">
        <t:btn-show-completed show="${showCompletedTasks}" currentPage="project?id=${project.id}"/>
    </div>

    <t:modal-edit-task/>
    <t:modal-create-task project="${project}"/>

</t:template>