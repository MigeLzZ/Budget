
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
<form method="post" action="<c:url value="/cards/getRecords/"/> ">
	<h1 class="k_h">Расход по карте</h1>
	<div class="r_block">
		<select name="selectedCard">
			<c:choose>
				<c:when test="${selectedCard != null}">
					<option desabled>Выберите карту</option>
				</c:when>
				<c:otherwise>
					<option selected desasled>Выберите карту</option>
				</c:otherwise>
			</c:choose>
			<c:forEach var="card" items="${cards}">
				<c:choose>
					<c:when test="${card.getCardNumber() == selectedCard}">
						<option selected value="${card.getCardNumber()}">${card.getCardNumber()}</option>
					</c:when>
					<c:otherwise>
						<option value="${card.getCardNumber()}">${card.getCardNumber()}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		<input type="month" value="2017-01" name="date">
		<input type="submit" value="показать">
	</div>
	<c:if test="${records != null}">
	<h2>Расходы по карте за ${mounth} ${year} года по карте ${cardNumber}</h2>
	<ul class="content">
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
		</c:if>
	</ul>
</form>
</body>
</html>