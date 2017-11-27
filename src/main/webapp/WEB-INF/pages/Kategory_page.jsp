<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Категории</title>
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
<section class="category content_over content">
	<h2>Категории:</h2>
	<h3>${categoryChangeMessage}</h3>
	<ul>
		<c:forEach var="category" items="${categories}">
			<li><a href="<c:url value="/categories/getRecords/${category.getId()}/${year}/${intMounth}"/> ">${category.getType()}<a href="<c:url value="/categories/changeCategory/${category.getId()}"/> ">X</a></a></li>
		</c:forEach>
	</ul>
</section>
<div >
	<a href="<c:url value="/categories/addCategory"/>" class="add_button kategory_add">Добавить +</a>
</div>
<div class="sort_mounth content ">
	<div >
		<a class="arrow_left" href="<c:url value="/categories/getRecordsByMounth/prev/${categoryId == null ? -1 : categoryId}/${year}/${prevMounth}"/> " name="arrow_left"></a>
	</div>
	<div class="mounths">
		<p>${mounth} ${year} год</p>
	</div>
	<div>
		<a class="arrow_right" href="<c:url value="/categories/getRecordsByMounth/next/${categoryId == null ? -1 : categoryId}/${year}/${nextMounth}"/>" name="arrow_right"></a>
	</div>
</div>
<section class="content pay_list content_over">
	<h2>Список расходов ${strCategory}:</h2>
	<ul>
		<c:if test="${records != null}">
			<c:forEach var="record" items="${records}">
				<li>${record.toString()}</li>
			</c:forEach>
		</c:if>
	</ul>
</section>
</body>
</html>