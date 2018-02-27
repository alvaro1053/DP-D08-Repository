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

<jstl:if test="${not empty announcements}">
<jstl:forEach items="${announcements}" var="announcement">
<table class="displayStyle">

<tr>
<td> <strong> <spring:message code="announcement.title" /> : </strong> </td>
<td>  <jstl:out value="${announcement.title}"> </jstl:out> </td>
</tr>


<tr>
<td> <strong> <spring:message code="announcement.description" /> : </strong> </td>
<td>  <jstl:out value="${announcement.description}"> </jstl:out> </td>
</tr>

<spring:message code="announcement.formatDisplay"
 var="format" />

<tr>
<td> <strong> <spring:message code="announcement.moment" /> : </strong> </td>
<td> <fmt:formatDate pattern="${format}" value="${announcement.moment}" /> </td>
</tr>


<security:authorize access="isAnonymous()">
<tr>
<td> <strong> <spring:message code="announcement.rende" /> : </strong> </td>
<td> <a href="rende/display.do?rendeId=${announcement.rende.id}"><jstl:out value="${announcement.rende.name}"/></a> </td>
</tr>
</security:authorize>

<security:authorize access="hasRole('USER')">
<tr>
<td> <strong> <spring:message code="announcement.rende" /> : </strong> </td>
<td> <a href="rende/user/display.do?rendeId=${announcement.rende.id}"><jstl:out value="${announcement.rende.name}"/></a> </td>
</tr>
</security:authorize>


<security:authorize access="hasRole('ADMIN')">
<tr>
<td> <strong> <spring:message code="announcement.rende" /> : </strong> </td>
<td> <a href="rende/admin/display.do?rendeId=${announcement.rende.id}"><jstl:out value="${announcement.rende.name}"/></a> </td>
</tr>
</security:authorize>



</table>
</jstl:forEach>
</jstl:if>

<security:authorize access="isAnonymous()">
	<jstl:if test="${empty announcements}">
		<h4><spring:message code = "announcement.noAnnouncements"/></h4>
		<a href="user/register.do"><spring:message code = "announcement.register"/></a>
		<spring:message code = "announcement.o"/> 
		<a href="security/login.do"><spring:message code = "announcement.singUp"/></a>
		<spring:message code = "announcement.newOne"/> 
		
		<br>
	</jstl:if>
</security:authorize>

<security:authorize access="hasAnyRole('USER','ADMIN')">
	<jstl:if test="${empty announcements}">
		<h4><spring:message code = "announcement.noAnnouncements"/></h4>
	</jstl:if>
</security:authorize>
