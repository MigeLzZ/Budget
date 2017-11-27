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
<div class="content last_expenses content_over">
	<h2>Последние траты</h2>
	<ul>
		<c:forEach var="record" items="${records}">
			<li><a href="<c:url value="/records/getRecord/0/${record.getId()}"/> ">${record.toString()}</a></li>
		</c:forEach>
	</ul>
	<a class="add_button" href="<c:url value="/records/addPaidRecord"/> " >Добавить трату+</a>
</div>
<div class="content near_expenses content_over">
	<h2> Ближайшие запланированные расходы</h2>
	<ul>
		<c:forEach var="plannedRecord" items="${plannedRecords}">
			<li><a href="<c:url value="/records/getRecord/1/${plannedRecord.getId()}"/> ">${plannedRecord.toString()}</a></li>
		</c:forEach>
	</ul>
	<a class="add_button" href="<c:url value="/records/addPlannedRecord"/> " >Добавить расход+</a>
</div>
<div class="content information content_over">
	<h2> Информация о картах</h2>
	<ul>
		<c:choose>
			<c:when test="${cards == null}">
				<li>У Вас нет карт</li>
			</c:when>
			<c:otherwise>
				<c:forEach var="card" items="${cards}">
					<li>${card.toString()}</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
	<a class="add_button" href="<c:url value="/addCard"/>" >Добавить карту+</a>
</div>
</body>
</html>