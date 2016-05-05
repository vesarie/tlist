<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template page="next7" pageTitle="Next 7 Days" projects="${projects}">
    <h2 class="page-header">Next 7 days</h2>

    <c:set var="padding"><div style="height: 30px"></div></c:set>

    <c:forEach var="i" begin="0" end="${fn:length(tasksByDate) - 1}">
        <c:set var="item" value="${tasksByDate[i]}"/>

        <h3 class="page-header">
            ${item.header} <small> ${item.subHeader}</small>
        </h3>

        <t:ul-task-list tasks="${item.tasks}" includeForm="${i == 0}"/>

        <c:if test="${i < fn:length(tasksByDate) - 1}">${padding}</c:if>
    </c:forEach>

    <div class="pull-right">
        <t:btn-show-completed show="${showCompletedTasks}" currentPage="next7"/>
    </div>

    <div class="modal fade" id="editTaskModal" tabindex="-1" role="dialog" aria-labelledby="editTaskModalLabel">
        <t:createEditTask action="editTask"/>
    </div>

</t:template>