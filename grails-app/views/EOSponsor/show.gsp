
<%@ page import="eventorganizer.EOSponsor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'EOSponsor.label', default: 'EOSponsor')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-EOSponsor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-EOSponsor" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list EOSponsor">
			
				<g:if test="${EOSponsorInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="EOSponsor.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${EOSponsorInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOSponsorInstance?.website}">
				<li class="fieldcontain">
					<span id="website-label" class="property-label"><g:message code="EOSponsor.website.label" default="Website" /></span>
					
						<span class="property-value" aria-labelledby="website-label"><g:fieldValue bean="${EOSponsorInstance}" field="website"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOSponsorInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="EOSponsor.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${EOSponsorInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOSponsorInstance?.logo}">
				<li class="fieldcontain">
					<span id="logo-label" class="property-label"><g:message code="EOSponsor.logo.label" default="Logo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${EOSponsorInstance?.sponsorship}">
				<li class="fieldcontain">
					<span id="sponsorship-label" class="property-label"><g:message code="EOSponsor.sponsorship.label" default="Sponsorship" /></span>
					
						<g:each in="${EOSponsorInstance.sponsorship}" var="s">
						<span class="property-value" aria-labelledby="sponsorship-label"><g:link controller="EOSponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${EOSponsorInstance?.id}" />
					<g:link class="edit" action="edit" id="${EOSponsorInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
