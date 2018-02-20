
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<spring:message code="user.terms" var="terms"/>

<script type="text/javascript">
function Terms(){
	alert("${terms}");
}
</script>
<form:form action="user/register.do" modelAttribute="actorForm" >


	<form:hidden path="id"/>
	<form:hidden path="version"/>

	<acme:textbox code="user.name" path="name"/>
	<br />
	
	<acme:textbox code="user.surname" path="surname"/>
	<br />
	
	<acme:textbox code="user.email" path="email"/>
	<br />
	
	
	<acme:textbox code="user.phone" path="phone"/>
	<br />
	
	<acme:textbox code="user.address" path="address"/>
	<br />
	
	<acme:textbox code="user.dateBirth" path="dateBirth"/>
	<br />
	
	
	<acme:textbox code="user.username" path="userAccount.username"/>
	<br />
	
	<acme:password code="user.password" path="userAccount.password"/>
	<br />
	
	<acme:password code="user.confirmPassword" path="confirmPassword"/>
	<br />
	
	<spring:message code ="user.check"/>
	<form:checkbox path="check"/>
	<form:errors path="check" cssClass="error" />
	<br />
	

	<input type="submit" name="save" id="save"
		value="<spring:message code="user.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="user.cancel" />"
		onclick="javascript: relativeRedir('');" />
	<br />
<jstl:out value="${message}"/>
</form:form>




