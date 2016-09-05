
<%@ page import="eventorganizer.EOEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'EOEvent.label', default: 'EOEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-EOEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-EOEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list EOEvent">
			
				<g:if test="${EOEventInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="EOEvent.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${EOEventInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.city}">
				<li class="fieldcontain">
					<span id="city-label" class="property-label"><g:message code="EOEvent.city.label" default="City" /></span>
					
						<span class="property-value" aria-labelledby="city-label"><g:fieldValue bean="${EOEventInstance}" field="city"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="EOEvent.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${EOEventInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.organizer}">
				<li class="fieldcontain">
					<span id="organizer-label" class="property-label"><g:message code="EOEvent.organizer.label" default="Organizer" /></span>
					
						<span class="property-value" aria-labelledby="organizer-label"><g:link controller="EOUser" action="show" id="${EOEventInstance?.organizer?.id}">${EOEventInstance?.organizer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.venue}">
				<li class="fieldcontain">
					<span id="venue-label" class="property-label"><g:message code="EOEvent.venue.label" default="Venue" /></span>
					
						<span class="property-value" aria-labelledby="venue-label"><g:fieldValue bean="${EOEventInstance}" field="venue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="EOEvent.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${EOEventInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="EOEvent.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${EOEventInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.volunteers}">
				<li class="fieldcontain">
					<span id="volunteers-label" class="property-label"><g:message code="EOEvent.volunteers.label" default="Volunteers" /></span>
					
						<g:each in="${EOEventInstance.volunteers}" var="v">
						<span class="property-value" aria-labelledby="volunteers-label"><g:link controller="EOUser" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.sponsorships}">
				<li class="fieldcontain">
					<span id="sponsorships-label" class="property-label"><g:message code="EOEvent.sponsorships.label" default="Sponsorships" /></span>
					
						<g:each in="${EOEventInstance.sponsorships}" var="s">
						<span class="property-value" aria-labelledby="sponsorships-label"><g:link controller="EOSponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.tasks}">
				<li class="fieldcontain">
					<span id="tasks-label" class="property-label"><g:message code="EOEvent.tasks.label" default="Tasks" /></span>
					
						<g:each in="${EOEventInstance.tasks}" var="t">
						<span class="property-value" aria-labelledby="tasks-label"><g:link controller="EOTask" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.messages}">
				<li class="fieldcontain">
					<span id="messages-label" class="property-label"><g:message code="EOEvent.messages.label" default="Messages" /></span>
					
						<g:each in="${EOEventInstance.messages}" var="m">
						<span class="property-value" aria-labelledby="messages-label"><g:link controller="EOMessage" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${EOEventInstance?.respondents}">
				<li class="fieldcontain">
					<span id="respondents-label" class="property-label"><g:message code="EOEvent.respondents.label" default="Respondents" /></span>
					
						<span class="property-value" aria-labelledby="respondents-label"><g:fieldValue bean="${EOEventInstance}" field="respondents"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${EOEventInstance?.id}" />
					<g:link class="edit" action="edit" id="${EOEventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
