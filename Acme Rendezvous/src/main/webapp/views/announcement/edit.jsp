

<%@page language="java" contentType="text/html; charset=ISO-8859-1"

	pageEncoding="ISO-8859-1"%>



<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>

<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



<form:form action="announcement/user/edit.do" modelAttribute="announcement">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="moment" />
	
	

	<form:label path="title">
		<spring:message code="announcement.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br/>
	<br/>
	
	<form:label path="description">
		<spring:message code="announcement.description" />:
	</form:label>
	<form:textarea path="description" />
	<form:errors cssClass="error" path="description" />
	<br/>
	<br/>
	
	
	<form:label path="rende">
		<spring:message code="announcement.rende" />:
	</form:label>
	<form:select path="rende">
		<form:option label ="-----" value="0"/>
		
		<form:options
			items="${rendes}"
			itemLabel = "name"
			itemValue = "id" />
	</form:select>
	<form:errors cssClass="error" path="rende" />
	<br/>
	<br/>

	<spring:message code="announcement.save" var="saveAnnouncement"  />
	<spring:message code="announcement.delete" var="deleteAnnouncement"  />
	<spring:message code="announcement.cancel" var="cancelAnnouncement"  />
	<spring:message code="announcement.confirm" var="confirmAnnouncement"  />
	
	
	<input type="submit" name="save"
		value="${saveAnnouncement}" />&nbsp; 	

	<input type="button" name="cancel"
		value="${cancelAnnouncement}"
		onclick="javascript: relativeRedir('announcement/list.do');" />
	<br />
</form:form>
