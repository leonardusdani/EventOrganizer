<%@ page import="eventorganizer.EOUser" %>



<div class="fieldcontain ${hasErrors(bean: EOUserInstance, field: 'fullName', 'error')} required">
	<label for="fullName">
		<g:message code="EOUser.fullName.label" default="Full Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fullName" required="" value="${EOUserInstance?.fullName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOUserInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="EOUser.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${EOUserInstance?.userName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOUserInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="EOUser.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${EOUserInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOUserInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="EOUser.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" required="" value="${EOUserInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOUserInstance, field: 'website', 'error')} ">
	<label for="website">
		<g:message code="EOUser.website.label" default="Website" />
		
	</label>
	<g:field type="url" name="website" value="${EOUserInstance?.website}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: EOUserInstance, field: 'bio', 'error')} ">
	<label for="bio">
		<g:message code="EOUser.bio.label" default="Bio" />
		
	</label>
	<g:textArea name="bio" cols="40" rows="5" maxlength="2000" value="${EOUserInstance?.bio}"/>
</div>

