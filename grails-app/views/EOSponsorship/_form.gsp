<%@ page import="eventorganizer.EOSponsorship" %>



<div class="fieldcontain ${hasErrors(bean: EOSponsorshipInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="EOSponsorship.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${eventorganizer.EOEvent.list()}" optionKey="id" required="" value="${EOSponsorshipInstance?.event?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorshipInstance, field: 'sponsor', 'error')} required">
	<label for="sponsor">
		<g:message code="EOSponsorship.sponsor.label" default="Sponsor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sponsor" name="sponsor.id" from="${eventorganizer.EOSponsor.list()}" optionKey="id" required="" value="${EOSponsorshipInstance?.sponsor?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorshipInstance, field: 'contributionType', 'error')} ">
	<label for="contributionType">
		<g:message code="EOSponsorship.contributionType.label" default="Contribution Type" />
		
	</label>
	<g:select name="contributionType" from="${EOSponsorshipInstance.constraints.contributionType.inList}" value="${EOSponsorshipInstance?.contributionType}" valueMessagePrefix="EOSponsorship.contributionType" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorshipInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="EOSponsorship.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${EOSponsorshipInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOSponsorshipInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="EOSponsorship.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${EOSponsorshipInstance?.notes}"/>
</div>

