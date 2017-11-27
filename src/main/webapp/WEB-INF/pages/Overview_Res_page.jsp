
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Обзор расходов</title>
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
<h1> Обзор расходов  за ${mounth} </h1>
<c:if test="${message != null}">
	<h2>${message}</h2>
</c:if>
<section class="left_section content content_over">
	<h2>Последние траты</h2>
	<p>
	<ul>
		<c:forEach var="record" items="${records}">
			<c:choose>
				<c:when test="${record.getPlanned() == true}">
					<li><a href="<c:url value="/records/getRecord/1/${record.getId()}"/> ">${record.toString()}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/records/getRecord/0/${record.getId()}"/> ">${record.toString()}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	</p>
	<a class="add_button" href="<c:url value="/records/addPaidRecord"/> " >Добавить расход+</a>
</section>
<main>
	<form method="post" action="<c:url value=" "/> ">
		<div class="resurs_block">
			<p>Сумма трат за месяц</p>
			<input type="text" value="${currentPay}" readonly>
		</div>
		<div class="resurs_block">
			<p>Лимит пользователя</p>
			<input type="text"  value="${limit}" readonly>
		</div>
		<div class="resurs_block">
			<p>Осталось</p>
			<input type="text" value="${balance}" readonly>
		</div>
		<div class="resurs_block">
			<p>Сумма всех запланированных трат на этот месяц</p>
			<input type="text"  value="${futurePay}" readonly>
		</div>
	</form>
</main>
<section class="right_section content content_over">
	<h2>Будущие траты</h2>
	<p>
	<ul>
		<c:forEach var="record" items="${futureRecords}">
			<c:choose>
				<c:when test="${record.getPlanned() == true}">
					<li><a href="<c:url value="/records/getRecord/1/${record.getId()}"/> ">${record.toString()}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/records/getRecord/0/${record.getId()}"/> ">${record.toString()}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	</p>
	<a class="add_button" href="<c:url value="/records/addPlannedRecord"/> " >Добавить трату+</a>
</section>
</body>
</html>
