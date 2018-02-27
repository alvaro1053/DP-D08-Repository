

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" 
	name="announcements" requestURI="announcement/list.do" id="row">



	<security:authorize access="hasRole('ADMIN')">	
		<display:column>
			<jstl:if test="${announcement.id != 0}">
				<spring:message code="announcement.confirm" var="confirmAnnouncement"  />
		  		
		  		<a href="announcement/admin/delete.do?AnnouncementId=${row.id}" onclick="return confirm('${confirmAnnouncement}')">
		  			<spring:message code="announcement.delete"/>
		  		</a>
			</jstl:if>
 		</display:column>
	</security:authorize>	


	<!-- Attributes -->
	
	
	<!-- title -->
	<spring:message code="announcement.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}"
		sortable="true" />

	<!-- description -->
	<spring:message code="announcement.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}"/>
	
	<!-- moment -->
	<spring:message code="announcement.format"
  		var="format" />
	<spring:message code="announcement.moment"
		var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="true" format = "${format}" />


	<!-- rende -->
	<spring:message code="announcement.rende"
		var="rendeHeader" />
	<display:column property="rende.name" title="${rendeHeader}" sortable="true"/> 


</display:table>




	<security:authorize access="hasRole('USER')">
			<a href="announcement/user/create.do"> <spring:message
				code="announcement.create" />
			</a>
	</security:authorize> 
	<br>
	
	<jstl:if test="${SucessDelete}">
		<strong><spring:message code="announcement.confirmed.delete"/></strong>
		<br>
		<br>
	</jstl:if>
	