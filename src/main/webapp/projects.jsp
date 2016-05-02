<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template page="projects" pageTitle="Edit projects" projects="${projects}">
    <h2 class="page-header">Edit projects</h2>

    <ul class="media-list">
        <c:forEach var="project" items="${projects}">
            <li class="media">
                <div class="media-body project">
                    <h4 class="media-heading" id="project${project.id}-name"><c:out value="${project.name}"/></h4>
                </div>

                <div class="media-right">
                    <button type="button" class="btn btn-default btn-sm"
                            data-toggle="modal" data-target="#editProjectModal"
                            data-project-id="${project.id}">Edit</button>
                </div>
            </li>
        </c:forEach>
    </ul>

    <button type="button" class="btn btn-default"
            data-toggle="modal" data-target="#createProjectModal">Create a project</button>

    <div class="modal fade" id="editProjectModal" tabindex="-1" role="dialog" aria-labelledby="editProjectModalLabel">
        <t:createEditProject action="editProject"/>
    </div>

    <div class="modal fade" id="createProjectModal" tabindex="-1" role="dialog" aria-labelledby="createProjectModalLabel">
        <t:createEditProject action="createProject"/>
    </div>

</t:template>