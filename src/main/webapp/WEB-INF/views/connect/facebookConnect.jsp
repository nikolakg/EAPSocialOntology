<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/spring-social/facebook/tags" prefix="facebook" %>
<%@ page session="false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3>Connect to Facebook</h3> 

<form action="<c:url value="/connect/facebook" />" method="POST">
	<input type="hidden" name="scope" value="publish_actions,user_friends,read_friendlists,read_stream,user_photos,offline_access" />
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect EAP Social Ontology with your Facebook account.</p>
              
	</div>
	<p><button type="submit"><img src="<c:url value="/resources/social/twitter/connect_light_medium_short.gif" />"/></button></p>
	<%// label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about EAP Social Ontology on your Facebook wall</label //%>    
</form>
