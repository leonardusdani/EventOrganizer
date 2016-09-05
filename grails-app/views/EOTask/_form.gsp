<%@ page import="eventorganizer.EOTask" %>



<div class="fieldcontain ${hasErrors(bean: EOTaskInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="EOTask.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${EOTaskInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOTaskInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="EOTask.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${EOTaskInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOTaskInstance, field: 'assignedTo', 'error')} ">
	<label for="assignedTo">
		<g:message code="EOTask.assignedTo.label" default="Assigned To" />
		
	</label>
	<g:select id="assignedTo" name="assignedTo.id" from="${eventorganizer.EOUser.list()}" optionKey="id" value="${EOTaskInstance?.assignedTo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOTaskInstance, field: 'dueDate', 'error')} ">
	<label for="dueDate">
		<g:message code="EOTask.dueDate.label" default="Due Date" />
		
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${EOTaskInstance?.dueDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: EOTaskInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="EOTask.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${eventorganizer.EOEvent.list()}" optionKey="id" required="" value="${EOTaskInstance?.event?.id}" class="many-to-one"/>
</div>

