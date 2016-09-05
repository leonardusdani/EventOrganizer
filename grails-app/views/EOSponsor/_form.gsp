<%@ page import="eventorganizer.EOSponsor" %>



<div class="fieldcontain ${hasErrors(bean: EOSponsorInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="EOSponsor.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${EOSponsorInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorInstance, field: 'website', 'error')} required">
	<label for="website">
		<g:message code="EOSponsor.website.label" default="Website" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="url" name="website" required="" value="${EOSponsorInstance?.website}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="EOSponsor.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="5000" value="${EOSponsorInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorInstance, field: 'logo', 'error')} ">
	<label for="logo">
		<g:message code="EOSponsor.logo.label" default="Logo" />
		
	</label>
	<input type="file" id="logo" name="logo" />
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorInstance, field: 'sponsorship', 'error')} ">
	<label for="sponsorship">
		<g:message code="EOSponsor.sponsorship.label" default="Sponsorship" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${EOSponsorInstance?.sponsorship?}" var="s">
    <li><g:link controller="EOSponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="EOSponsorship" action="create" params="['EOSponsor.id': EOSponsorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship')])}</g:link>
</li>
</ul>

</div>

