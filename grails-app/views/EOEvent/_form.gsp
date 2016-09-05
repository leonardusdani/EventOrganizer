<%@ page import="eventorganizer.EOEvent" %>



<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="EOEvent.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${EOEventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'city', 'error')} required">
	<label for="city">
		<g:message code="EOEvent.city.label" default="City" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="city" required="" value="${EOEventInstance?.city}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="EOEvent.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" required="" value="${EOEventInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'organizer', 'error')} required">
	<label for="organizer">
		<g:message code="EOEvent.organizer.label" default="Organizer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="organizer" name="organizer.id" from="${eventorganizer.EOUser.list()}" optionKey="id" required="" value="${EOEventInstance?.organizer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'venue', 'error')} required">
	<label for="venue">
		<g:message code="EOEvent.venue.label" default="Venue" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="venue" required="" value="${EOEventInstance?.venue}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'startDate', 'error')} required">
	<label for="startDate">
		<g:message code="EOEvent.startDate.label" default="Start Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="startDate" precision="day"  value="${EOEventInstance?.startDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'endDate', 'error')} required">
	<label for="endDate">
		<g:message code="EOEvent.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="endDate" precision="day"  value="${EOEventInstance?.endDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'volunteers', 'error')} ">
	<label for="volunteers">
		<g:message code="EOEvent.volunteers.label" default="Volunteers" />
		
	</label>
	<g:select name="volunteers" from="${eventorganizer.EOUser.list()}" multiple="multiple" optionKey="id" size="5" value="${EOEventInstance?.volunteers*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'sponsorships', 'error')} ">
	<label for="sponsorships">
		<g:message code="EOEvent.sponsorships.label" default="Sponsorships" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${EOEventInstance?.sponsorships?}" var="s">
    <li><g:link controller="EOSponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="EOSponsorship" action="create" params="['EOEvent.id': EOEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'EOSponsorship.label', default: 'EOSponsorship')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'tasks', 'error')} ">
	<label for="tasks">
		<g:message code="EOEvent.tasks.label" default="Tasks" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${EOEventInstance?.tasks?}" var="t">
    <li><g:link controller="EOTask" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="EOTask" action="create" params="['EOEvent.id': EOEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'EOTask.label', default: 'EOTask')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'messages', 'error')} ">
	<label for="messages">
		<g:message code="EOEvent.messages.label" default="Messages" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${EOEventInstance?.messages?}" var="m">
    <li><g:link controller="EOMessage" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="EOMessage" action="create" params="['EOEvent.id': EOEventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'EOMessage.label', default: 'EOMessage')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: EOEventInstance, field: 'respondents', 'error')} ">
	<label for="respondents">
		<g:message code="EOEvent.respondents.label" default="Respondents" />
		
	</label>
	
</div>

