

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

<jstl:choose>
<jstl:when test="${permisos}">

<form:form action="rende/user/edit.do" modelAttribute="rendeForm">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	
	<jstl:choose>
	<jstl:when test="${rendeForm.isDraft == true || isDeleted}">
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
	<form:errors cssClass="error" path="adultOnly" />
	<br />
	<br />
	
	<acme:selectMultiple items="${rendes}" itemLabel="name" code="rende.linked" path="linked"/>
	<br />
	<br />
	
	
	<jstl:if test="${rendeForm.id ==0 }">
	<!-- QUESTIONS -->
	
	
	<fieldset>

	
	<legend> <form:label path="questions"> <spring:message code="rende.questions" />: </form:label> </legend>



		<div id="list1">
		<table class="displayStyle">
			<tr>
			<th>  <spring:message code="rende.question" /> :  </th>
			<th> </th>
			 </tr>
			
			<jstl:choose> 
			<jstl:when test="${empty rendeForm.questions}">
			<tr class="list-item">
			
			
			
			<td>  	<form:input path="questions[0].question" /> <form:errors cssClass="error" path="questions[0].question" /></td>
			<td>	<a href="#" onclick="event.preventDefault();"
					class="list-remove"> <spring:message code="rende.question.remove" /> </a> </td>
			</tr>
			
			</jstl:when>
			<jstl:otherwise>
			<jstl:forEach items="${rendeForm.questions}" var="question" varStatus="i" begin="0">
   			 <tr class="list-item">
			<td> <form:input path="questions[${i.index}].question" /> <form:errors cssClass="error" path="questions[${i.index}].question" /></td>
     		<td>	<a href="#" onclick="event.preventDefault();"
					class="list-remove"> <spring:message code="rende.question.remove" /> </a> </td>
	    </tr>
            <br />
        </jstl:forEach>
			</jstl:otherwise>
			</jstl:choose>
			
		</table>
		<a href="#" onclick="event.preventDefault();" class="list-add"><spring:message code="rende.question.add" /></a>
		</div>
		<br />
		<form:errors cssClass="error" path="questions" />

</fieldset>

</jstl:if>


<jstl:if test="${finalModeOption}">
	<spring:message code="rende.isDraft" />
	<form:checkbox path="isDraft" name="draft" value="true"/>
	<br />
</jstl:if>
	<br />
	
	<spring:message code="rende.save" var="saveRende"  />
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

	</jstl:when>
	<jstl:otherwise>
		<acme:selectMultiple items="${rendes}" itemLabel="name" code="rende.linked" path="linked"/>
	<br />
	<br />
	
	<spring:message code="rende.save" var="saveRende"  />
	<spring:message code="rende.cancel" var="cancelRende"  />
		
	<input type="submit" name="save" value="${saveRende}" />&nbsp; 
 
 	<input type="button" name="cancel"
  		value="${cancelRende}"
  onclick="javascript: relativeRedir('rende/user/list.do');" />
 <br />
	
	</jstl:otherwise>
	</jstl:choose>
	


</form:form>

</jstl:when>
<jstl:otherwise>
<spring:message code="rende.permision" />
</jstl:otherwise>
</jstl:choose>

<script>
    $(document).ready(function() {
        $("#list1").dynamiclist();
    });
 </script>