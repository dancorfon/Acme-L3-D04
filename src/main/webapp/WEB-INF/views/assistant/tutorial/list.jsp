<%--
- list.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="assistant.tutorial.list.label.title" path="title"  width="20%"/>
	<acme:list-column code="assistant.tutorial.list.label.abstract$" path="abstract$" width="60%" />
	<acme:list-column code="assistant.tutorial.list.label.goal" path="goals" width="20%" />

</acme:list>

<acme:button code="assistant.tutorial.list.button.create" action="/assistant/tutorial/create"/>

