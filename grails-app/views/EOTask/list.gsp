
<%@ page import="eventorganizer.EOTask" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'EOTask.label', default: 'EOTask')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-EOTask" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-EOTask" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'EOTask.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="notes" title="${message(code: 'EOTask.notes.label', default: 'Notes')}" />
					
						<th><g:message code="EOTask.assignedTo.label" default="Assigned To" /></th>
					
						<g:sortableColumn property="dueDate" title="${message(code: 'EOTask.dueDate.label', default: 'Due Date')}" />
					
						<th><g:message code="EOTask.event.label" default="Event" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${EOTaskInstanceList}" status="i" var="EOTaskInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${EOTaskInstance.id}">${fieldValue(bean: EOTaskInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: EOTaskInstance, field: "notes")}</td>
					
						<td>${fieldValue(bean: EOTaskInstance, field: "assignedTo")}</td>
					
						<td><g:formatDate date="${EOTaskInstance.dueDate}" /></td>
					
						<td>${fieldValue(bean: EOTaskInstance, field: "event")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${EOTaskInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
