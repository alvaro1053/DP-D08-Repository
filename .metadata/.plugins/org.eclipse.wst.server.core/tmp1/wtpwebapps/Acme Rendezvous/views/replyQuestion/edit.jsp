

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

<form:form action="replyQuestion/user/edit.do" modelAttribute="replyQuestionForm">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="questions" />
	
	<jstl:forEach items="${replyQuestionForm.questions}" var="question" varStatus="i" begin="0">
	<jstl:out value="${i.index +1}. ${question.question}"/>
	<acme:textbox code="replyQuestion.reply" path="replies[${i.index}].reply"/>
	</jstl:forEach>
	
	
<br/>
	
	



	<spring:message code="replyQuestion.save" var="saveQuestion"  />
	<spring:message code="replyQuestion.delete" var="deleteQuestion"  />
	<spring:message code="replyQuestion.cancel" var="cancelQuestion"  />
	<spring:message code="replyQuestion.confirm" var="confirmQuestion"  />
	
	
	<input type="submit" name="save"
		value="${saveQuestion}" />&nbsp; 
		

	<input type="button" name="cancel"
		value="${cancelQuestion}"
		onclick="javascript: relativeRedir('rende/user/list.do');" />
	<br />
</form:form>
</jstl:when>
 <jstl:otherwise>
 <spring:message code="replyQuestion.error" />
</jstl:otherwise>
</jstl:choose>
