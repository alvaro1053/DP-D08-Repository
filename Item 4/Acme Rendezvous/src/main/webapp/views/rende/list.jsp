

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	

	
<security:authorize access="hasRole('USER')">
	<form action="rende${uri}/list.do" method="get">
	
	<input type="radio" name="filter" value="0"> <spring:message code="rende.all" />
	<input type="radio" name="filter" value="1"> <spring:message code="rende.filter.creator" />
	<input type="radio" name="filter" value="2"> <spring:message code="rende.filter.RZVP" />

	<br />
	<spring:message code="rende.choose" var="choose"/>
	<input type="submit" value="${choose}">
	</form>
</security:authorize>

<!-- Message successful RSVP -->

<jstl:if test="${successful}">
	<h3><spring:message code="rende.successful"></spring:message></h3>
</jstl:if>

<jstl:if test="${successfullyCancelled}">
	<h3><spring:message code="rende.successfullyCancelled"></spring:message></h3>
</jstl:if>

<jstl:if test="${youCantRSVP}">
	<h3><spring:message code="rende.cant.cancell"></spring:message></h3>
</jstl:if>


<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" 
	name="rendes" requestURI="rende${uri}/list.do" id="row">
		
	<!-- Esta es una forma provisional (NIVEL C)  -->
	<security:authorize access="hasRole('USER')">
		<display:column>
			<jstl:if test="${principal.id == row.user.id && row.isDeleted == false}">
				<a href="rende/user/edit.do?rendeId=${row.id}"><spring:message code ="rende.edit"/></a>
			</jstl:if>
		</display:column>
	</security:authorize>
	
	
	<security:authorize access="hasRole('ADMIN')">
		<spring:message code="rende.confirm" var="confirmRende"  />
		<display:column>
				<a href="rende/admin/delete.do?rendeId=${row.id}" onclick="return confirm('${confirmRende}')"><spring:message code ="rende.delete" /></a>
		</display:column>
	</security:authorize>


	<!-- name -->
	<spring:message code="rende.name"
		var="nameHeader" />
	<display:column title="${nameHeader}"
		sortable="true">
		<a href="rende${uri}/display.do?rendeId=${row.id}">
			<jstl:out value="${row.name}"/>
		</a>
	</display:column>
	<!-- description -->
	<spring:message code="rende.description"
		var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"
		sortable="true" />
	
	<!-- moment --> <!-- format para internacionalizacion -->
 	<spring:message code="rende.format"
  	var="format" />
 	<spring:message code="rende.moment"
  	var="momentHeader" />
 	<display:column property="moment" title="${momentHeader}"
  	sortable="true" format = "${format}"/>
  	
  	<!-- Creator -->
  	<spring:message code="rende.creator"
  	var="creator" />
  	<display:column title="${creator}">  <a href="user${uri}/display.do?userId=${row.user.id}">
				 	<jstl:out value ="${row.user.name} ${row.user.surname}" /> </a></display:column>
	
		<!-- Linked -->
	<spring:message code="rende.linked"
  	var="linked" />
	<display:column title="${linked}">
	<ul>
		<jstl:forEach items="${row.linked}" var="linked"> 
			 <li>
				 <a href="rende/display.do?rendeId=${linked.id}">
				 	<jstl:out value ="${linked.name}" />
				 </a>
			 </li>
		</jstl:forEach>
	</ul>
	</display:column>
	
	<!-- Attendants -->
	<spring:message code="rende.attendants"
  	var="attendants" />
	<display:column title="${attendants}">
	<ul>
		<jstl:forEach items="${row.attendants}" var="attendant"> 
			 <li>
				 <a href="user/displayAttendant.do?userId=${attendant.id}&rendeId=${row.id}">
				 	<jstl:out value ="${attendant.userAccount.username}" />
				 </a>
			 </li>
		</jstl:forEach>
	</ul>
	</display:column>
	
	<!-- Info -->
	<spring:message code="rende.info" var="info"/>
	<spring:message code="rende.adultOnly" var="adultOnly"/>
	<spring:message code="rende.isDeleted" var="isDeleted"/>
	
	<display:column title="${info}">
	<jsp:useBean id="now" class="java.util.Date"/>
		<jstl:if test="${row.moment lt now}">
			<spring:message code="rende.passed"
  			var="passed" />
  			
			<img class="alarmImg" src="images/alarm.png" alt="${passed}" title="${passed}"/>
		</jstl:if>
		<jstl:if test="${row.adultOnly}">
			<img class="alarmImg" src="images/cancel.png" alt="${adultOnly}" title="${adultOnly}"/>
		</jstl:if>
		<jstl:if test="${row.isDeleted}">
			<img class="alarmImg" src="images/deleted.png" alt="${isDeleted}" title="${isDeleted}"/>
		</jstl:if>
	</display:column>
	
	
	
	<!-- RSVP -->
 <security:authorize access="hasRole('USER')">
  <display:column>
   <jstl:if test="${not row.attendants.contains(principal)}">
    <jstl:if test="${not (row.moment lt now)}">
     <a href="replyQuestion/user/create.do?rendeId=${row.id}"><spring:message code ="rende.rsvp"/></a>
    </jstl:if>
   </jstl:if>
   
   <jstl:if test="${row.attendants.contains(principal)}">
    <a href="rende/user/rsvp.do?rendeId=${row.id}"><spring:message code ="rende.cancelRSVP"/></a>
   </jstl:if>
  </display:column>
 </security:authorize>
 
	<!-- Questions -->
	
	<display:column>
			<a href="question${uri}/list.do?rendeId=${row.id}"> <spring:message
					code="rende.questions" />
			</a>
		

</display:column>

	
	
	<!-- Linked -->
	<display:table name="${row.linked}" id="linked" pagesize="15" class="displaytag">
		<display:column property="name" title="${nameHeader}" sortable="false" />
	</display:table>
	
	<display:column>
		<a href="announcement/display.do?rendeId=${row.id}"> <spring:message
				code="rende.announcement" />
		</a>
	</display:column>
	
	<security:authorize access="hasRole('USER')">
	
			<display:column>
				<jstl:if test="${principal.rSVPS.contains(row)}">
				<a href="comment/user/list.do?rendeId=${row.id}"> <spring:message
						code="rende.comments" />
				</a>
						</jstl:if>
			</display:column>

	</security:authorize>
	
	
	
	<security:authorize access="hasRole('ADMIN')">
	
			<display:column>

				<a href="comment/admin/list.do?rendeId=${row.id}"> <spring:message
						code="rende.comments" />
				</a>
			</display:column>

	</security:authorize>
	
	
</display:table>


<security:authorize access="hasRole('USER')">

<a href="rende/user/create.do"><spring:message code ="rende.newRende"/></a>
</security:authorize>
<br/>


