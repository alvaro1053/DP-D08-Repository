

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
<jstl:choose>
<jstl:when test="${permisos}">


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" 
	name="comments" requestURI="comment${uri }/list.do" id="row">



	<security:authorize access="hasRole('USER')">
	
	<!-- Edit -->
	<jstl:if test="${not rende.isDeleted}">
	<spring:message code="comment.edit" var="edit" />
	<display:column title="${edit}"
		sortable="false">
	<jstl:if test="${principal.comments.contains(row) }">
 		<a href="comment/user/edit.do?commentId=${row.id}">${edit}</a>
 	</jstl:if>	
	</display:column>
	</jstl:if>
	</security:authorize>
	
	
		<security:authorize access="hasRole('ADMIN')">
	
	<!-- Delete -->
	<spring:message code="comment.delete" var="delete" />
	<spring:message code="comment.confirmComment" var="confirmComment" />
	<display:column title="${delete}"
		sortable="false">
 		<a href="comment/admin/delete.do?commentId=${row.id}" onclick="return confirm('${confirmComment}')">${delete}</a>
	</display:column>
	</security:authorize>
	
<!-- Attributes -->
		
	<!-- text -->
	<spring:message code="comment.text" var="textHeader" />
	<display:column property="text" title="${textHeader}"
		sortable="true" />
		
	<!-- picture -->
	<spring:message code="comment.picture" var="pictureHeader" />
	<spring:message code="comment.pictureError" var="pictureError" />
	<display:column title="${pictureHeader}" sortable="true" > <img src="${row.picture}"  width="auto" height="200"></display:column> 


	<!-- Writer -->
		<spring:message code="comment.writeBy"
	var="writer" />
	<display:column title="${writer}" property="user.name" href="user/user/display.do" paramId="userId" paramProperty="user.id">  </display:column>)


	<!-- moment -->
	<spring:message code="comment.format"
  		var="format" />
	<spring:message code="comment.moment"
		var="momentHeader" />
	<display:column property="moment" title="${momentHeader}"
		sortable="true" format = "${format}" />
	
	<!-- Replies of the comment -->
	<spring:message code="comment.listReply"
		var="listReply" />
	<display:column title="${listReply}">
	
	<display:table pagesize="3" class="displaytag" 
	name="${row.repliesComments}" requestURI="comment${uri}/list.do" id="reply">
	
	<spring:message code="comment.reply2"
		var="reply2" />
	<display:column property="reply" title="${reply2}"/>
	
	<spring:message code="comment.delete"
		var="delete" />
	
	<display:column title="${writer}" property="user.name" href="user${uri}/display.do" paramId="userId" paramProperty="user.id">  </display:column>)
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column title="${delete}"> <a href="replyComment/admin/delete.do?replyCommentId=${reply.id}" onclick="return confirm('${confirmComment}')">${delete}</a> </display:column>
	</security:authorize>)
	
	</display:table>
	</display:column>
	<security:authorize access="hasRole('USER')">
	
	
	
	<!-- Reply -->
	<display:column>
		<jstl:if test="${not rende.isDeleted}">
			<a href="replyComment/user/create.do?commentId=${row.id}"> <spring:message
					code="comment.reply" />
			</a>
		</jstl:if>
	</display:column>
	
			<a href="comment/user/create.do"> <spring:message
				code="comment.create" />
			</a>
	</security:authorize> 


</display:table>

<br/>
<security:authorize access="hasRole('USER')">
<jstl:if test="${not rende.isDeleted}">
<spring:message code="comment.create"
	var="create" />
 <a href="comment/user/create.do?rendeId=${rende.id}">${create}</a>
 </jstl:if>
 </security:authorize>
 </jstl:when>
 <jstl:otherwise>
 <spring:message code="comment.dirtyHacker" />
</jstl:otherwise>
</jstl:choose>

