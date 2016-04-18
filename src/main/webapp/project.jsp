<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template pageTitle="${project.name}" projects="${projects}">
    <h2 class="page-header">${project.name}</h2>

    <ul class="media-list">
        <c:forEach var="task" items="${tasks}">
            <li class="media">
                <div class="media-left">
                    <input type="checkbox" aria-label=""/>
                </div>

                <div class="media-body task">
                    <h4 class="media-heading" id="task${task.id}-name"><c:out value="${task.name}"/></h4>
                </div>

                <div class="media-right">
                    <button type="button" class="btn btn-default btn-sm"
                            data-toggle="modal" data-target="#editTaskModal"
                            data-task-id="${task.id}"
                            data-task-priority="${task.priority.integer}"
                            data-task-schedule="${task.schedule}">Edit</button>
                </div>
            </li>
        </c:forEach>
    </ul>

    <button type="button" class="btn btn-default"
            data-toggle="modal" data-target="#createTaskModal">Create a new task</button>

    <div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog" aria-labelledby="editTaskModalLabel">    
        <t:createEditTask action="editTask"/>
    </div>

    <div class="modal fade" id="createTaskModal" tabindex="-1" role="dialog" aria-labelledby="createTaskModalLabel">
        <t:createEditTask action="createTask" project="${project}"/>
    </div>

</t:template>