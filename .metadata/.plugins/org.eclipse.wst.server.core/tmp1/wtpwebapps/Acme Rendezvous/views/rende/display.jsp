<%--
 * 
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>


<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jstl:choose>
<jstl:when test="${permisos == true}">
<h2>  <jstl:out value="${rende.name}" /> </h2>
<br>


<jstl:if test="${rende.isDeleted}">
	<h4><spring:message code="rende.isDeleted"/></h4>
</jstl:if>

<table class="displayStyle">


<tr>
<td> <strong> <spring:message code="rende.description" /> : </strong> </td>
<td>  <jstl:out value="${rende.description}"> </jstl:out> </td>
</tr>



<tr>
<td> <strong> <spring:message code="rende.picture" /> : </strong> </td>
<td> <img src="${rende.picture}"  width="auto" height="200"> </td>
</tr>




<tr>
<spring:message code="rende.momentPattern" var="pattern" />
<td> <strong> <spring:message code="rende.moment" /> : </strong> </td>
<td> <fmt:formatDate value="${rende.moment}" pattern="${pattern}" /> </td>
</tr>


<tr>
<td> <strong> <spring:message code="rende.user" /> : </strong> </td>
<td> <a href="user/display.do?userId=${rende.user.id}"><jstl:out value="${rende.user.name}"/> </a> </td>
</tr>



<!-- Comments -->

<security:authorize access="hasRole('USER')">
<jstl:if test="${principal.rSVPS.contains(rende)}">
	<tr>
		<spring:message code="rende.comments" var="comments" />
		<td> <strong> <spring:message code="rende.comments"/> : </strong> </td>
		<td> <a href="comment/user/list.do?rendeId=${rende.id}">${comments} </a> </td>
	</tr>
</jstl:if>
</security:authorize>


<security:authorize access="hasRole('ADMIN')">
	<tr>
		<spring:message code="rende.comments" var="comments" />
		<td> <strong> <spring:message code="rende.comments"/> : </strong> </td>
		<td> <a href="comment/admin/list.do?rendeId=${rende.id}">${comments} </a> </td>
	</tr>
</security:authorize>



<!-- Attendants -->
<tr>
<td> <strong> <spring:message code="rende.attendants" /> : </strong> </td>
<td> 
<jstl:choose>
<jstl:when test="${not empty rende.attendants}"> 
<ul>
<jstl:forEach items="${rende.attendants}" var="user">
<li> <a href="user${uri}/display.do?userId=${user.id}"><jstl:out value="${user.name}"/></a></li>
</jstl:forEach>
</ul> 
</jstl:when>
<jstl:otherwise>
<spring:message code="rende.empty" />
</jstl:otherwise>
</jstl:choose>
</td>
</tr>

<!-- Linkeds -->
<tr>
<td> <strong> <spring:message code="rende.linked" /> : </strong> </td>
<td> 
<jstl:choose>
<jstl:when test="${not empty rende.linked}"> 
<ul>
<jstl:forEach items="${rende.linked}" var="rende">
<li> <a href="rende${uri}/display.do?rendeId=${rende.id}"><jstl:out value="${rende.name}"/></a></li>
</jstl:forEach>
</ul> 
</jstl:when>
<jstl:otherwise>
<spring:message code="rende.empty" />
</jstl:otherwise>
</jstl:choose>
</td>
</tr>

</table>

<table>

<tr>
<th> <strong> <spring:message code="rende.coordenates" /> : </strong> </th>
</tr>

<tr>
<td> <strong> <spring:message code="rende.coordenates.latitude" /> : </strong> </td>
<td>  <jstl:out value="${rende.coordenates.latitude}"> </jstl:out> </td>
</tr>


<tr>
<td> <strong> <spring:message code="rende.coordenates.longitude" /> : </strong> </td>
<td>  <jstl:out value="${rende.coordenates.longitude}"> </jstl:out> </td>
</tr>

</table>
</jstl:when>
<jstl:otherwise>
<spring:message code="rende.permision" />
</jstl:otherwise>
</jstl:choose>

