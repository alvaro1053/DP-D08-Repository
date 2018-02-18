

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="comment/user/edit.do" modelAttribute="comment">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	<form:hidden path="user" />
	<form:hidden path="rende" />
	
	

	<form:label path="text">
		<spring:message code="comment.text" />:
	</form:label>
	<form:input path="text" />
	<form:errors cssClass="error" path="text" />
	<br/>
	<br/>
	
	<form:label path="picture">
		<spring:message code="comment.picture" />:
	</form:label>
	<form:textarea path="picture" />
	<form:errors cssClass="error" path="picture" />
	<br/>
	<br/>
	



	<spring:message code="comment.save" var="saveComment"  />
	<spring:message code="comment.delete" var="deleteComment"  />
	<spring:message code="comment.cancel" var="cancelComment"  />
	<spring:message code="comment.confirm" var="confirmComment"  />
	
	
	<input type="submit" name="save"
		value="${saveComment}" />&nbsp; 
		
	<jstl:if test="${comment.id != 0}">
  	<input type="submit" name="delete" value="${deleteComment}"
   		onclick="return confirm('${confirmComment}')" />&nbsp;
	</jstl:if>
 

	<input type="button" name="cancel"
		value="${cancelComment}"
		onclick="javascript: relativeRedir('comment/list.do');" />
	<br />
</form:form>

