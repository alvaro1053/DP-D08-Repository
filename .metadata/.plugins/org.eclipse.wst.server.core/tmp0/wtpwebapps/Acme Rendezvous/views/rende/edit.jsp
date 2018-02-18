

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


	<!-- isDraft (si NO es draft no se debe poder editar)-->
	<!-- isDeleted (si ha sido borrada no se debe poder editar)-->
<jstl:if test="${permission }">

<jstl:choose>
<jstl:when test="${rende.isDraft == false || rende.isDeleted == true}">
<h3><spring:message code="rende.nopermission" /></h3>
</jstl:when>
<jstl:otherwise>

<!-- Tiene que ser el usuario que HAYA CREADO la rende quien pueda editarla y además el ADMIN puede eliminar (lo miraremos más tarde)-->
<form:form action="rende/user/edit.do" modelAttribute="rende">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user" />
	<form:hidden path="announcement" />
	<form:hidden path="comment" />
	<form:hidden path="question" />
	<form:hidden path="linked" /><!-- no estoy seguro de si es hidden -->
		
	
<!-- ATRIBUTES -->
	<!-- name -->
	<form:label path="name">
		<spring:message code="rende.name" />:
	</form:label>
	<form:input path="name"/>
	<form:errors cssClass="error" path="name" />
	<br />
	<br />
	
	<!-- description -->
	<form:label path="description">
		<spring:message code="rende.description" />:
	</form:label>
	<form:input path="description"/>
	<form:errors cssClass="error" path="description" />
	<br />
	<br />

	<!-- moment -->	
	<form:label path="moment">
		<spring:message code="rende.moment" />:
	</form:label>
	<form:input path="moment"/>
	<form:errors cssClass="error" path="moment" />
	<br />
	<br />
	
	<!-- picture -->
	<form:label path="picture">
		<spring:message code="rende.picture" />:
	</form:label>
	<form:input path="picture"/>
	<form:errors cssClass="error" path="picture" />  <!-- URL -->
	<br />
	<br />
	
	<!-- coordenates -->
	<fieldset>

	<legend>
		<form:label path="coordenates">
			<spring:message code="rende.coordenates" /> : 
		</form:label>
	</legend>

	<form:label path="coordenates.latitude">
			<spring:message code="rende.coordenates.latitude" />:
	</form:label>
	<form:input path="coordenates.latitude" />
	<form:errors cssClass="error" path="coordenates.latitude" />
	<br />

	<form:label path="coordenates.longitude">
			<spring:message code="rende.coordenates.longitude" />:
	</form:label>
	<form:input path="coordenates.longitude" />
	<form:errors cssClass="error" path="coordenates.longitude" />
	<br />

	</fieldset>

	<br />
	<br />
	
	<form:checkbox path="adultOnly" name="adultOnly" value="adultOnly"/>
	<br />
	<br />

	

	<spring:message code="rende.save" var="saveRende"  /><!-- Lo probamos así al principio y luego lo cambiamos para guardar en modo draft y final -->
	<spring:message code="rende.cancel" var="cancelRende"  />
	<spring:message code="rende.delete" var="deleteRende"  />
	<spring:message code="rende.confirm" var="confirmRende"  />
	
	
	<input type="submit" name="save" value="${saveRende}" />&nbsp; 
 		<jstl:if test="${rende.id != 0}">
  	<input type="submit" name="delete" value="${deleteRende}"
   		onclick="return confirm('${confirmRende}')" />&nbsp;
	</jstl:if>
 
 	<input type="button" name="cancel"
  		value="${cancelRende}"
  onclick="javascript: relativeRedir('rende/user/list.do');" />
 <br />

</form:form>

</jstl:otherwise>
</jstl:choose>

</jstl:if>