<%@tag description="Show completed tasks (button)" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="show" required="true" type="java.lang.Boolean"%>
<%@attribute name="currentPage" required="true"%>
<c:choose>
    <c:when test="${show == null || !show}">
        <a href="${currentPage}&showCompletedTasks">Show completed tasks</a>
    </c:when>
    <c:otherwise>
        <a href="${currentPage}">Hide completed tasks</a>
    </c:otherwise>
</c:choose>