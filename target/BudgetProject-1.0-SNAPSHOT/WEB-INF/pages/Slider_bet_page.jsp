
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Обзор по времени</title>
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
<div class="sort_mounth content ">
	<div >
		<a class="arrow_left" href="<c:url value="/records/getRecordsByMounth/prev/${year}/${prevMounth}"/> " name="arrow_left"></a>
	</div>
	<div class="mounths">
		<p>${mounth} ${year} год</p>
	</div>
	<div>
		<a class="arrow_right" href="<c:url value="/records/getRecordsByMounth/next/${year}/${nextMounth}"/>" name="arrow_right"></a>
	</div>
</div>
<p>
<div >
	<ul class="content fixed content_over">
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
</div>
</p>
</body>
</html>

