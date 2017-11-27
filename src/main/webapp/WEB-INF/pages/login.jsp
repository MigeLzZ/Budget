<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Вход</title>
	<link rel="stylesheet" href="<c:url value="/resources/css8/style.css"/>"/>
</head>
<body>
<h1> Вход в систему: </h1>
<c:if test="${loginFailed}"><h3>Что-то пошло не так. Проверьте логин и пароль.</h3></c:if>
<form method="post" action="<c:url value="/login"/> ">
	<div class="r_block not_marg">
		<input type="text" placeholder="Введите логин" name="username" required pattern="[a-zA-Z]([a-zA-Z0-9]{1,20})">
	</div>
	<div class="r_block not_marg">
		<input type="password" placeholder="Введите пароль" maxlength="15" name="password" required>
	</div>
	<div class="sub_button not_marg">
		<input type="submit" value="Продолжить">
		<a class="but_reg" href="<c:url value="/registration"/>" >Регистрация</a>
	</div>
</form>
</body>
</html>