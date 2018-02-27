

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')" var="isAdmin"/>


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" 
	name="questions" requestURI="question${uri}/list.do" id="row">

	
	<spring:message code="question.confirm" var="confirmQuestion" />
	<!-- Delete  FOR USERS-->
	<security:authorize access="hasRole('USER')">
	<jstl:if test="${principal.rendes.contains(row.rende) }">
	<spring:message code="question.delete" var="delete" />
	<display:column title="${delete}"
		sortable="false">
 		<a href="question$/user/delete.do?questionId=${row.id}" onclick="return confirm('${confirmQuestion}')">${delete}</a>
	</display:column>
	</jstl:if>
	</security:authorize>
	
	<!-- Delete FOR ADMINS-->
	<security:authorize access="hasRole('ADMIN')">
	<spring:message code="question.delete" var="delete" />
	<display:column title="${delete}"
		sortable="false">
 		<a href="question/admin/delete.do?questionId=${row.id}"onclick="return confirm('${confirmQuestion}')">${delete}</a>
	</display:column>
	</security:authorize>
	
<!-- Attributes -->
		
	<!-- question -->
	<spring:message code="question.question" var="textHeader" />
	<display:column property="question" title="${textHeader}"
		sortable="true" />
	
	<!-- Replies of the question -->
	<spring:message code="question.listReply"
		var="listReply" />
	<display:column title="${listReply}">
	
	<display:table pagesize="3" class="displaytag" 
	name="${row.replyQuestions}" requestURI="question${uri}/list.do" id="reply">
	
	<spring:message code="question.reply2"
		var="reply2" />
	<display:column property="reply" title="${reply2}"/>
	
	<spring:message code="question.delete"
		var="delete" />
	
	<display:column title="${writer}" property="user.name" href="user${uri}/display.do" paramId="userId" paramProperty="user.id">  </display:column>)
	
	</display:table>
	</display:column>
	
	


</display:table>

<br/>


