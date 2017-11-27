
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Регистрация</title>
	<link rel="stylesheet" href="<c:url value="/resources/css8/style.css"/>"/>
</head>
<body>
<h1> Регистрация пользователя </h1>
<form method="post" action="<c:url value="/registration"/> ">
	<h3>${message}</h3>
	<div class="r_block">
		<input type="email" name="mail" placeholder="Введите e-mail" required>
	</div>
	<div class="r_block">
		<input type="text" name="login" placeholder="Введите логин" required pattern="[a-zA-Z]([a-zA-Z0-9]{1,20})">
	</div>
	<div class="r_block">
		<input type="password" name="password" placeholder="Введите пароль" maxlength="15" required>
	</div>
	<div class="sub_button not_marg">
		<input type="submit" value="Регистрация">
		<a class="but_avt" href="<c:url value="/login"/>" >Авторизация</a>
	</div>
</form>
</body>
</html>


