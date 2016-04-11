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
                            data-task-priority="${task.priority}"
                            data-task-schedule="${task.schedule}">Edit</button>
                </div>
            </li>
        </c:forEach>
    </ul>
    
    <div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog" aria-labelledby="editTaskModalLabel">
        <jsp:include page="editTask.jsp"/>
    </div>

<!--    <div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog" aria-labelledby="editTaskModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="editTask" method="get" data-async data-target="#editTaskModal" data-id-input="#editTask-id">
                    <input type="hidden" name="id" id="editTask-id" value=""/>
                
                    <input type="hidden" name="id" id="edit"value=""/>

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editTaskModalLabel">Edit task</h4>
                    </div>

                    <div class="modal-body">
                        <p>Loading...</p>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>-->

    <!--
    <div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog" aria-labelledby="editTaskModalLabel">
        <div class="modal-dialog" role="document">
            <form action="editTask" method="post">
                <div class="modal-content">
                    <input type="hidden" name="id" value=""/>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editTaskModalLabel">Edit task</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="editTask-name" class="control-label">Name:</label>
                            <input type="text" class="form-control" id="editTask-name"/>
                        </div>
                        <div class="form-group">
                            <label for="editTask-schedule" class="control-label">Schedule:</label>
                            <input type="text" class="form-control" id="editTask-schedule"/>
                        </div>
                        <div class="form-group">
                            <label for="editTask-priority" class="control-label">Priority:</label>
                            <select class="form-control" id="editTask-priority">
                                <option value="1" id="editTask-priority-1">1</option>
                                <option value="2" id="editTask-priority-2">2</option>
                                <option value="3" id="editTask-priority-3">3</option>
                                <option value="4" id="editTask-priority-4">4</option>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    -->
</t:template>