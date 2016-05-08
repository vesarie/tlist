<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template page="today" pageTitle="Today" projects="${projects}">
    <h2 class="page-header">Today</h2>

    <t:ul-task-list tasks="${tasks}" projectMap="${projectMap}"/>

    <div class="pull-right">
        <t:btn-show-completed show="${showCompletedTasks}" currentPage="today"/>
    </div>

    <t:modal-edit-task/>

</t:template>