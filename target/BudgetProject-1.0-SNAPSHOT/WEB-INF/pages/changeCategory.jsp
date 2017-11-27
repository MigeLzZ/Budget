
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Просмотр расходов</title>
    <link rel="stylesheet" href="<c:url value="/resources/css8/style.css"/>"/>
</head>
<header>
    <nav>
        <a href="<c:url value="/main"/> ">Главная</a>
        <a href="<c:url value="/records/Overview/"/>">Обзор расходов</a>
        <a href="<c:url value="/categories/"/>">Категории</a>
        <a href="<c:url value="/records/mounthlyRecords"/>">По времени</a>
        <a href="<c:url value="/privateRoom/"/>">Личный кабинет</a>
        <a href="<c:url value="/cards/getRecords/"/>">Расходы по карте</a>
        <a href="<c:url value="/logout"/>">Выйти(${pageContext.request.userPrincipal.name})</a>
    </nav>
</header>
<form method="post" action="<c:url value="/categories/changeCategory"/> ">
    <section class="see_pay">
        <h3>${categoryExistMessage}</h3>
        <h1>Название категории</h1>
        <div class="r_block change_button">
            <input type="text" name="categoryName" value="${category.getType()}" id="cat_text">
            <input type="hidden" name="id" value="${category.getId()}">
            <button name="clear_b" value="0" class="push_">Удалить</button>
            <button name="repair_b" value="1" class="add_b">Изменить</button>
        </div>
    </section>
</form>
</body>
</html>