<%@tag description="Create/edit project form" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="action" required="true"%>
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <!-- The AJAX part is in js/tlist.js -->
        <form action="${action}" method="post" id="${action}"
              data-async data-target="#${action}Modal"
              data-saved="${saved == null ? 'false' : saved}">
            <input type="hidden" name="id" id="${action}-id" value="${id == null ? '' : id}"/>

            <c:set var="title" value="${action == 'editProject' ? 'Edit project' : 'Create project'}"/>

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="${action}ModalLabel">${title}</h4>
            </div>

            <div class="modal-body">
                <div class="form-group ${nameErrorMsg == null ? '' : 'has-error'}">
                    <label for="${action}-name" class="control-label">Name:</label>
                    <input type="text" class="form-control" name="name" id="${action}-name"
                           value="<c:out value="${name}" default=""/>"
                           aria-describedby="${action}-name-msg"/>
                    <span id="${action}-name-msg" class="help-block">
                        <c:out value="${nameErrorMsg}" default=""/>
                    </span>
                </div>
            </div>

            <div class="modal-footer">
                <c:if test="${action == 'editProject'}">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger btn-submit-on-click" data-form="#deleteProject">Delete</button>
                    <button type="submit" class="btn btn-primary">Save changes</button>
                </c:if>

                <c:if test="${action == 'createProject'}">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Create</button>
                </c:if>
            </div>
        </form>

        <c:if test="${action == 'editProject'}">
            <form action="deleteProject" method="post" id="deleteProject">
                <input type="hidden" name="id" id="deleteProject-id" value="${id == null ? '' : id}"/>
            </form>
        </c:if>
    </div>
</div>