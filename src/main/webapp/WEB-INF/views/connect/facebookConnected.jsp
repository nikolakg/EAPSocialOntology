<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%// <h3>Connect to Facebook</h3> %>
<h3>Συνδεση στο  Facebook</h3>
<form id="disconnect" method="post">
	<div class="formInfo">
		<%// <p>  %>
		<%//	EAP Social Ontology is connected to your Facebook account.%>
		<%//	Click the button if you wish to disconnect.%>
		<%//</p> %>
                <p>
			Η εφαρμογή Eap Social Ontology έχει συνδεθεί στον λογαριασμός σας στο Facebook.
			Πατήστε το κουμπι αν θέλετε να αποσυνδεθεί.
		</p>
	</div>
	<%// <button type="submit">Disconnect</button>  %>
        <button type="submit">Αποσύνδεση</button>
	<input type="hidden" name="_method" value="delete" />
</form>

<a href="<c:url value="/facebook"/>">Δείτε το προφίλ σας στο Facebook</a>
