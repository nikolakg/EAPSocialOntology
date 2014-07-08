<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<script src="http://platform.twitter.com/anywhere.js?id=7yWLgCOuQhIpPyffm0o2Vg&v=1" type="text/javascript"></script>
<script type="text/javascript">
  twttr.anywhere(function (T) {
    T(".feed").linkifyUsers();
  });    
</script>
      
<h3>Ontology Inference </h3>
Music Friends are:
<ul>
<c:forEach items="${musicFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>
Food Friends are:
<ul>
<c:forEach items="${foodFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>