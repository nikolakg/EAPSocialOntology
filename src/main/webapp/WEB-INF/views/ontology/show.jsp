<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h2>EAP Ontology Show </h2>
Interest Categories are:
<ul>
<c:forEach items="${interests}" var="interestType">
		<li><c:out value="${interestType.localName}"/></lo>
</c:forEach>
</ul>

<p/>&nbsp;
<p/>&nbsp;