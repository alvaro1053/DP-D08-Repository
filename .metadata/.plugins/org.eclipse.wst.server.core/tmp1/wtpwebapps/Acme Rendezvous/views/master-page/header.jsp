<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<div>
	<a href="/Acme-Rendezvous"><img src="images/logo.png" alt="Sample Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="dashboard/admin/display.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
						
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="rende/list.do"><spring:message code="master.page.rendeList" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
		<li><a class="fNiv" href="rende/admin/list.do"><spring:message code="master.page.rendeList" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="rende/user/list.do"><spring:message code="master.page.rendeList" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
		<li><a class="fNiv" href="announcement/list.do"><spring:message code="master.page.announcementList" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
		<li><a class="fNiv" href="announcement/list.do"><spring:message code="master.page.announcementList" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message	code="master.page.announcement" /></a>
				<ul>
					<li class="arrow"></li>
						<li><a href="announcement/list.do"><spring:message code="master.page.announcementList" /></a></li>
						<li><a href="announcement/user/display.do"><spring:message code="master.page.announcementListUser" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
		<li><a class="fNiv" href="user/list.do"><spring:message code="master.page.userList" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
		<li><a class="fNiv" href="user/user/list.do"><spring:message code="master.page.userList" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
		<li><a class="fNiv" href="user/admin/list.do"><spring:message code="master.page.userList" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

