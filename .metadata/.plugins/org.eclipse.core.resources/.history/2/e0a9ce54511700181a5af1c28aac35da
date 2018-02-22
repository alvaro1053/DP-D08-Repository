

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

<security:authorize access="hasRole('ADMIN')" var="isAdmin"/>
<jstl:choose>
<jstl:when test="${permisos || isAdmin }">

<form:form action="comment/user/edit.do" modelAttribute="commentForm">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="rende" />
	
	
<acme:textarea code="comment.text" path="text"/>
<br/>
	
<acme:textbox code="comment.picture" path="picture"/>
<br/>
	



	<spring:message code="comment.save" var="saveComment"  />
	<spring:message code="comment.delete" var="deleteComment"  />
	<spring:message code="comment.cancel" var="cancelComment"  />
	<spring:message code="comment.confirm" var="confirmComment"  />
	
	
	<input type="submit" name="save"
		value="${saveComment}" />&nbsp; 
<security:authorize access="hasRole('ADMIN')">
	<jstl:if test="${commentForm.id != 0}">
  	<input type="submit" name="delete" value="${deleteComment}"
   		onclick="return confirm('${confirmComment}')" />&nbsp;
	</jstl:if>
 </security:authorize>

	<input type="button" name="cancel"
		value="${cancelComment}"
		onclick="javascript: relativeRedir('comment/user/list.do?rendeId=${commentForm.rende}');" />
	<br />
</form:form>
</jstl:when>
 <jstl:otherwise>
 <spring:message code="comment.dirtyHacker2" />
</jstl:otherwise>
</jstl:choose>
