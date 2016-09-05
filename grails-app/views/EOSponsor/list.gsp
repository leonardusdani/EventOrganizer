
<%@ page import="eventorganizer.EOSponsor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'EOSponsor.label', default: 'EOSponsor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-EOSponsor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-EOSponsor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'EOSponsor.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="website" title="${message(code: 'EOSponsor.website.label', default: 'Website')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'EOSponsor.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="logo" title="${message(code: 'EOSponsor.logo.label', default: 'Logo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${EOSponsorInstanceList}" status="i" var="EOSponsorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${EOSponsorInstance.id}">${fieldValue(bean: EOSponsorInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: EOSponsorInstance, field: "website")}</td>
					
						<td>${fieldValue(bean: EOSponsorInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: EOSponsorInstance, field: "logo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${EOSponsorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
