
<%@ page import="eventorganizer.EOUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'EOUser.label', default: 'EOUser')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-EOUser" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-EOUser" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="fullName" title="${message(code: 'EOUser.fullName.label', default: 'Full Name')}" />
					
						<g:sortableColumn property="userName" title="${message(code: 'EOUser.userName.label', default: 'User Name')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'EOUser.password.label', default: 'Password')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'EOUser.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="website" title="${message(code: 'EOUser.website.label', default: 'Website')}" />
					
						<g:sortableColumn property="bio" title="${message(code: 'EOUser.bio.label', default: 'Bio')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${EOUserInstanceList}" status="i" var="EOUserInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${EOUserInstance.id}">${fieldValue(bean: EOUserInstance, field: "fullName")}</g:link></td>
					
						<td>${fieldValue(bean: EOUserInstance, field: "userName")}</td>
					
						<td>${fieldValue(bean: EOUserInstance, field: "password")}</td>
					
						<td>${fieldValue(bean: EOUserInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: EOUserInstance, field: "website")}</td>
					
						<td>${fieldValue(bean: EOUserInstance, field: "bio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${EOUserInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
