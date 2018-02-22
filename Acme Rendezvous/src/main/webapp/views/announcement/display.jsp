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


<table class="displayStyle">

<tr>
<td> <strong> <spring:message code="announcement.title" /> : </strong> </td>
<td>  <jstl:out value="${announcement.title}"> </jstl:out> </td>
</tr>


<tr>
<td> <strong> <spring:message code="announcement.description" /> : </strong> </td>
<td>  <jstl:out value="${announcement.description}"> </jstl:out> </td>
</tr>


<tr>
<td> <strong> <spring:message code="announcement.moment" /> : </strong> </td>
<td> <fmt:formatDate value="${announcement.moment}" /> </td>
</tr>

<tr>
<td> <strong> <spring:message code="announcement.rende" /> : </strong> </td>
<td> <a href="rende/user/display.do?rendeId=${announcement.rende.id}"><jstl:out value="${announcement.rende.name}"/></a> </td>
</tr>





</table>


