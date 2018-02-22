

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


	
<form:form action="rende/user/edit.do" modelAttribute="rendeForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="isDeleted" />
	
	
	<jstl:choose>
	<jstl:when test="${rendeForm.isDraft == true || rendeForm.isDeleted == false}">
<!-- ATRIBUTES -->
	<!-- name -->
	<acme:textbox code="rende.name" path="name"/>
	<br />
	<br />
	
	<!-- description -->
	<acme:textarea code="rende.description" path="description"/>
	<br />
	<br />
	
	<!-- moment -->
	<acme:textbox code="rende.moment" path="moment"/>
	<br />
	<br />
	
	<!-- picture -->
	<acme:textbox code="rende.picture" path="picture"/>
	<br />
	<br />
	
	<!-- coordenates -->
	<fieldset>

	<legend>
		<form:label path="coordenates">
			<spring:message code="rende.coordenates" /> : 
		</form:label>
	</legend>
	<acme:textbox code="rende.coordenates.latitude" path="coordenates.latitude"/>
	<br/>

	<acme:textbox code="rende.coordenates.longitude" path="coordenates.longitude"/>
	<br/>

	</fieldset>

	<br />
	<br />
	<spring:message code="rende.adultOnly" />
	<form:checkbox path="adultOnly" name="adult" value="true"/>
	<br />
	<br />
	
	<acme:selectMultiple items="${rendes}" itemLabel="name" code="rende.linked" path="linked"/>
	<br />
	<br />

	
	<spring:message code="rende.isDraft" />
	<form:checkbox path="isDraft" name="draft" value="true"/>
	<br />
	<br />

	</jstl:when>
	<jstl:otherwise>
		<form:hidden path="isDraft" />
		<form:hidden path="adultOnly" />
		<form:hidden path="description" />
		<form:hidden path="name" />
		<acme:selectMultiple items="${rendes}" itemLabel="name" code="rende.linked" path="linked"/>
	<br />
	<br />
	
	</jstl:otherwise>
	</jstl:choose>
	
	<spring:message code="rende.save" var="saveRende"  /><!-- Lo probamos así al principio y luego lo cambiamos para guardar en modo draft y final -->
	<spring:message code="rende.cancel" var="cancelRende"  />
	<spring:message code="rende.delete" var="deleteRende"  />
	<spring:message code="rende.confirm" var="confirmRende"  />
	
	
	<input type="submit" name="save" value="${saveRende}" />&nbsp; 
 	
 	<jstl:if test="${rendeForm.id != 0}">	
  	<input type="submit" name="delete" value="${deleteRende}"
   		onclick="return confirm('${confirmRende}')" />&nbsp;
	</jstl:if>
 
 	<input type="button" name="cancel"
  		value="${cancelRende}"
  onclick="javascript: relativeRedir('rende/user/list.do');" />
 <br />

</form:form>