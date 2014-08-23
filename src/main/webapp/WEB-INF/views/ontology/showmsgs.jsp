<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h2><c:out value="${msgtype}"/> Friend Messages</h2>
<ul style="clear">
<c:forEach items="${friendMessages}" var="message">
		<li><c:out value="${message.string}"/></lo>
</c:forEach>
</ul>

<p/>&nbsp;
<p/>&nbsp;