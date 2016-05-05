<%@tag description="Sidebar list item (navigation link)" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="isActive" required="true" type="java.lang.Boolean"%>
<%@attribute name="href" required="true"%>
<c:choose>
    <c:when test="${isActive}">
        <li class="active"><a href="${href}"><jsp:doBody/> <span class="sr-only">(current)</span></a></li>
    </c:when>
    <c:otherwise>
        <li><a href="${href}"><jsp:doBody/></a></li>
    </c:otherwise>
</c:choose>