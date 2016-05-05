<%@tag description="Task list item (one task)" pageEncoding="UTF-8" trimDirectiveWhitespaces="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="tasks" required="true" type="java.util.List"%>
<%@attribute name="includeForm" required="false" type="java.lang.Boolean"%>
<ul class="media-list">
    <c:forEach var="task" items="${tasks}">
        <li class="media${task.completed ? ' text-muted' : ''}" id="task${task.id}-li">
            <c:choose>
                <c:when test="${!task.completed}">
                    <c:set var="checked" value=""/>
                    <c:set var="b" value=""/>
                    <c:set var="a" value=""/>
                </c:when>
                <c:otherwise>
                    <c:set var="checked" value="checked"/>
                    <c:set var="b" value="<del>"/>
                    <c:set var="a" value="</del>"/>
                </c:otherwise>
            </c:choose>

            <div class="media-left">
                <input type="checkbox" class="taskCheckbox" name="completedTasks" value="${task.id}" aria-label="" ${checked}/>
            </div>

            <div class="media-body task">
                ${b}<h4 class="media-heading" id="task${task.id}-name"><c:out value="${task.name}"/></h4>${a}
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

<c:if test="${includeForm == null || includeForm}">
    <form action="setCompleted" method="post" id="setComplete">
        <input type="hidden" id="setComplete-id" name="id" value=""/>
        <input type="hidden" id="setComplete-value" name="value" value=""/>
    </form>
</c:if>