<%@tag description="Create/edit task form" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="action" required="true"%>
<%@attribute name="project" required="false" type="tlist.models.Project"%>
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <!-- The AJAX part is in js/tlist.js -->
        <form action="${action}" method="post" id="${action}"
              data-async data-target="#${action}Modal"
              data-saved="<c:out value="${saved}" default="false"/>">
            <input type="hidden" name="id" id="${action}-id" value="${id == null ? '' : id}"/>
            <input type="hidden" name="project" value="${project == null ? '' : project.id}"/>

            <c:set var="title" value="${action == 'editTask' ? 'Edit task' : 'Create task'}"/>

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="${action}ModalLabel">${title}</h4>
            </div>

            <div class="modal-body">
                <div class="form-group ${nameErrorMsg == null ? '' : 'hasError'}">
                    <label for="${action}-name" class="control-label">Name:</label>
                    <input type="text" class="form-control" name="name" id="${action}-name"
                           value="<c:out value="${name}" default=""/>"
                           aria-describedby="${action}-name-msg"/>
                    <span id="${action}-name-msg" class="help-block">
                        <c:out value="${nameErrorMsg}" default=""/>
                    </span>
                </div>

                <div class="form-group ${scheduleErrorMsg == null ? '' : 'hasError'}">
                    <label for="${action}-schedule" class="control-label">Schedule:</label>
                    <input type="text" class="form-control" name="schedule" id="${action}-schedule"
                           data-datepicker value="<c:out value="${schedule}" default=""/>"
                           aria-describedby="${action}-schedule-msg"/>
                    <span id="${action}-schedule-msg" class="help-block">
                        <c:out value="${scheduleErrorMsg}" default=""/>
                    </span>
                </div>

                <div class="form-group ${priorityErrorMsg == null ? '' : 'hasError'}">
                    <label for="${action}-priority" class="control-label">Priority:</label>
                    <select class="form-control" name="priority" id="${action}-priority" aria-describedby="${action}-priority-msg">

                        <c:if test="${priority == null}">
                            <c:set var="priority" value="4"/>
                        </c:if>

                        <c:forEach var="p" items="${priorities}">
                            <c:choose>
                                <c:when test="${priority == p}">
                                    <option value="${p}" id="${action}-priority-${p}" selected>${p}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${p}" id="${action}-priority-${p}">${p}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <span id="${action}-priority-msg" class="help-block">
                        <c:out value="${priorityErrorMsg}" default=""/>
                    </span>
                </div>
            </div>

            <div class="modal-footer">
                <c:if test="${action == 'editTask'}">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger btn-submit-on-click" data-form="#deleteTask">Delete</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </c:if>

                <c:if test="${action == 'createTask'}">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Create</button>
                </c:if>
            </div>
        </form>

        <c:if test="${action == 'editTask'}">
            <form action="deleteTask" method="post" id="deleteTask">
                <input type="hidden" name="id" id="deleteTask-id" value="${id == null ? '' : id}"/>
            </form>
        </c:if>
    </div>
</div>