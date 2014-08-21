<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3>Connect to Facebook</h3>
<form id="disconnect" method="post">
	<div class="formInfo">
		<p>  
		EAP Social Ontology is connected to your Facebook account.
		Click the button if you wish to disconnect.
		</p>	</div>
	
        <button type="submit">Disconnect</button>
	<input type="hidden" name="_method" value="delete" />
</form>

<a href="<c:url value="/facebook"/>">See you Facebook profile</a>
