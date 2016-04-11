<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <form action="editTask" method="post" data-async data-target="#editTaskModal">
            <input type="hidden" name="id" id="editTask-id" value="${task.id}"/>

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editTaskModalLabel">Edit task</h4>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <label for="editTask-name" class="control-label">Name:</label>
                    <input type="text" class="form-control" id="editTask-name" value="${task.name}"/>
                </div>
                <div class="form-group">
                    <label for="editTask-schedule" class="control-label">Schedule:</label>
                    <input type="text" class="form-control" id="editTask-schedule" value="${task.schedule}"/>
                </div>
                <div class="form-group">
                    <label for="editTask-priority" class="control-label">Priority:</label>
                    <select class="form-control" id="editTask-priority">
                        <c:forEach var="p" items="${priorities}">
                            <c:choose>
                                <c:when test="${task.priority == p}">
                                    <option value="${p}" id="editTask-priority-${p}" selected>${p}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${p}" id="editTask-priority-${p}">${p}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </form>
    </div>
</div>