
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" href="<c:url value="/resources/css8/style.css"/>"/>
	</head>
	<body>
<nav>
		<a href="<c:url value="/main"/> ">Главная</a>
		<a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
		<a href="<c:url value="/categories/"/>">Категории</a>
		<a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
		<a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
		<a href="<c:url value="/cards/getRecords/"/>">Расходы по карте</a>
		<a href="<c:url value="/logout"/>">Выйти(${pageContext.request.userPrincipal.name})</a>
	</nav>
		<h1> Личный кабинет</h1>
		<form method="post" action="<c:url value="/privateRoom/action/changeUsername"/> ">
		<div class="person_name"> 
			<p>Логин</p>
			<input type="text" name="p_name" value="${user.username}" pattern="[a-zA-Z]([a-zA-Z0-9]{1,20})">
			<b>${message}</b>
			<button type="submit">изменить</button>
		</div>
		</form>
		<form method="post" action="<c:url value="/privateRoom/action/changeMail "/> ">
		<div class="person_email">
			<p>E-mail</p>
			<input type="e-mail" name="p_email" value="${user.mail}">
			<button type="submit">Изменить</button>
		</div>
		</form>
		<form method="post" action="<c:url value=" /privateRoom/action/changeLimit"/> ">
		<div class="person_limit">
			<p>Лимит</p>
			<input type="text" name="p_limit" value="${user.mounthlyLimit}" pattern="[0-9]{1,10}">
			<button type="submit">Изменить</button>
		</div>
		</form>
	</body>
</html>