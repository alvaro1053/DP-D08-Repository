

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
	name="rendes" requestURI="${requestURI}" id="row">
	
	
	<!-- Attributes -->
	
	<display:column>
   <a href="rende/user/edit.do?rendeId=${row.id}"> <spring:message code="rende.edit" /></a>
	</display:column>

	<!-- name -->
	<spring:message code="rende.name"
		var="nameHeader" />
	<display:column property="name" title="${nameHeader}"
		sortable="true" />
		
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
	
		

 

	
</display:table>


<a href="rende/user/create.do"><spring:message code ="rende.newRende"/></a>
