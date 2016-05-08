<%@tag description="Create/edit task modal" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="project" required="false" type="tlist.models.Project"%>
<c:set var="p" value=""/>
<c:if test="${project != null}">
    <c:set var="p">[${project.id}]</c:set>
</c:if>
<div data-task-projects="${p}" class="modal fade" id="createTaskModal" tabindex="-1" role="dialog" aria-labelledby="createTaskModalLabel">
    <t:createEditTask action="createTask"/>
</div>