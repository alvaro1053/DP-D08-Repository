

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
	name="users" requestURI="users/list.do" id="row">
	
	
	<!-- userAccount -->
	<spring:message code="users.username"
		var="usernameHeader" />
	<display:column property="userAccount.username" title="${usernameHeader}"
		sortable="true" />
		
		
	<!-- name -->
	<spring:message code="users.name"
		var="nameHeader" />
	<display:column property="name" title="${nameHeader}"
		sortable="true" />

	<!-- surname -->
	<spring:message code="users.surname"
		var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}"
		sortable="true" />
		
	<!-- phone -->
	<spring:message code="users.phone"
		var="phoneHeader" />
	<display:column property="phone" title="${phoneHeader}"
		sortable="true" />
		
	<!-- address -->
	<spring:message code="users.address"
		var="addressHeader" />
	<display:column property="address" title="${addressHeader}"
		sortable="true" />

	<!-- email -->
	<spring:message code="users.email"
		var="emailHeader" />
	<display:column property="email" title="${emailHeader}"
		sortable="true" />
		
	<!-- dateBirth -->
	<spring:message code="users.dateBirth"
		var="dateBirthHeader" />
	<display:column property="dateBirth" title="${dateBirthHeader}"
		sortable="true" />	
	
	<display:column>
		<a href="users/display.do?userId=${row.id}"> <spring:message
			code="users.display" />
		</a>
	</display:column>
		
</display:table>
