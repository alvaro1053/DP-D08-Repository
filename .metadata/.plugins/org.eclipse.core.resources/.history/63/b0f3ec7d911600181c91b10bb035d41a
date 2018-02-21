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


<table class="displayStyle" >


<tr>
<td> <strong> <spring:message code="user.username" /> : </strong> </td>
<td>  <jstl:out value = "${user.userAccount.username}"/> &nbsp;  </td>
</tr>

<tr>
<td> <strong> <spring:message code="user.name" /> : </strong> </td>
<td>  <jstl:out value = "${user.name}"/> &nbsp;  </td>
</tr>

<tr>
<td> <strong> <spring:message code="user.surname" /> : </strong> </td>
<td>  <jstl:out value = "${user.surname}"/> &nbsp;  </td>
</tr>

<tr>
<td> <strong> <spring:message code="user.phone" /> : </strong> </td>
<td>  <jstl:out value="${user.phone}" /> &nbsp; </td>
</tr>

<tr>
<td> <strong> <spring:message code="user.address" /> : </strong> </td>
<td>  <jstl:out value="${user.address}" /> &nbsp; </td>
</tr>

<tr>
<td> <strong> <spring:message code="user.email" /> : </strong> </td>
<td> <jstl:out value ="${user.email}" /> &nbsp; </td>
</tr>

<tr>
<td> <strong> <spring:message code="user.dateBirth" /> : </strong> </td>
<td> <jstl:out value ="${user.dateBirth}" /> &nbsp; </td>
</tr>




<spring:message code="user.showRende" var="showRende"/>

<tr>
<td> <strong> <spring:message code="user.rendes" /> : </strong> </td>
<td> 

<jstl:choose>
<jstl:when test="${not empty user.rendes}"> 
<ul>
<jstl:forEach items="${user.rendes}" var="rende">
<li> <jstl:out value="${rende.name}"/> &nbsp; (<a href="rende/display.do?rendeId=${rende.id}"> ${showRende} </a>) </li>
</jstl:forEach>
</ul> 
</jstl:when>
<jstl:otherwise>

<spring:message code="user.rendes.empty" />

</jstl:otherwise>
</jstl:choose>

</td>
</tr>


</table>








