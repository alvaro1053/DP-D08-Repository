

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

<form:form action="replyComment/user/edit.do" modelAttribute="replyComment">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="user" />
	<form:hidden path="comment" />
	
	
<acme:textarea code="replyComment.reply" path="reply"/>
<br/>
	
	



	<spring:message code="replyComment.save" var="saveComment"  />
	<spring:message code="replyComment.delete" var="deleteComment"  />
	<spring:message code="replyComment.cancel" var="cancelComment"  />
	<spring:message code="replyComment.confirm" var="confirmComment"  />
	
	
	<input type="submit" name="save"
		value="${saveComment}" />&nbsp; 
		

	<input type="button" name="cancel"
		value="${cancelComment}"
		onclick="javascript: relativeRedir('comment/user/list.do?rendeId=${replyComment.comment.rende.id}');" />
	<br />
</form:form>
</jstl:when>
 <jstl:otherwise>
 <spring:message code="replyComment.error" />
</jstl:otherwise>
</jstl:choose>
