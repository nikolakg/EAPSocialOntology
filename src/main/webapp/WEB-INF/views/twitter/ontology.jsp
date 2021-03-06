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
Music Friends [because of <a href="<c:url value="/ontology/msgs/music"/>">Messages</a>] are:
<ul>
<c:forEach items="${musicFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>

Science Friends [because of <a href="<c:url value="/ontology/msgs/science"/>">Messages</a>] are:
<ul>
<c:forEach items="${scienceFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>

Food Friends  [because of <a href="<c:url value="/ontology/msgs/food"/>">Messages</a>]  are:
<ul>
<c:forEach items="${foodFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>
Health Friends [because of <a href="<c:url value="/ontology/msgs/health"/>">Messages</a>] are :
<ul>
<c:forEach items="${healthFriends}" var="ontology">
    <li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
                    
</ul>
Education Friends [because of <a href="<c:url value="/ontology/msgs/edu"/>">Messages</a>] are :
<ul>
<c:forEach items="${eduFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>
Sport Friends  [because of <a href="<c:url value="/ontology/msgs/sport"/>">Messages</a>] are:
<ul>
<c:forEach items="${sportFriends}" var="ontology">
		<li><c:out value="${ontology.localName}"/></lo>
</c:forEach>
</ul>

<c:url var="tweetUrl" value="/twitter/owltweet" />
<sf:form action="${tweetUrl}" method="post" modelAttribute="messageForm">
	<p><h3>Post tweet(s) to Ontology Group Friends</h3></p>
	<sf:label path="to">To: </sf:label>
        <sf:select path="friend">
            <sf:option value="NONE" label="--- Select ---"/>
            <sf:option value="musicFriends" label="Music Friends"/>
            <sf:option value="foodFriends" label="Food Friends"/>
            <sf:option value="healthFriends" label="Health Friends"/>
            <sf:option value="eduFriends" label="Education Friends"/>
            <sf:option value="scienceFriends" label="Science Friends"/>
            <sf:option value="sportFriends" label="Sport Friends"/>
        </sf:select><br/>
	<sf:textarea path="text" rows="2" cols="80"/><br/>
	<input type="submit" value="Post Tweet"/>
</form>
</sf:form>
<p/>&nbsp;
<p/>&nbsp;