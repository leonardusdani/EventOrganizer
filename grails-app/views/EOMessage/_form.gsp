<%@ page import="eventorganizer.EOMessage" %>



<div class="fieldcontain ${hasErrors(bean: EOMessageInstance, field: 'subject', 'error')} required">
	<label for="subject">
		<g:message code="EOMessage.subject.label" default="Subject" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="subject" required="" value="${EOMessageInstance?.subject}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOMessageInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="EOMessage.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="content" cols="40" rows="5" maxlength="2000" required="" value="${EOMessageInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOMessageInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="EOMessage.parent.label" default="Parent" />
		
	</label>
	<g:select id="parent" name="parent.id" from="${eventorganizer.EOMessage.list()}" optionKey="id" value="${EOMessageInstance?.parent?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOMessageInstance, field: 'author', 'error')} required">
	<label for="author">
		<g:message code="EOMessage.author.label" default="Author" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="author" name="author.id" from="${eventorganizer.EOUser.list()}" optionKey="id" required="" value="${EOMessageInstance?.author?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOMessageInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="EOMessage.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${eventorganizer.EOEvent.list()}" optionKey="id" required="" value="${EOMessageInstance?.event?.id}" class="many-to-one"/>
</div>

