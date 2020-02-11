<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IT ROAD. Вакансии</title>
		<meta charset="UTF-8"/>		
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<link rel="stylesheet" href="../css/menu1.css" type="text/css"/>
		<link rel="stylesheet" href="../css/media.css" type="text/css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"/>
		<link rel="stylesheet" href="../css/vacancy_style.css" type="text/css"/>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="../css/media_contacts.css" type="text/css"/>
		<link rel="stylesheet" type="text/css" href="../css/modal_cont1act1.css" />
	</head>
	
	<body>

		<c:if test="${id==null}">
			<c:redirect url="/html/authorization.jsp"/>;
		</c:if>
		<header>
			<nav class="menu">
				<input type="checkbox" name="toggle" id="menu" class="toggleMenu"/>
				<label for="menu" class="toggleMenu"><i class="fa fa-bars"></i>Меню</label>
				<ul>
					<li><a href="main.jsp"><i class="fa fa-bookmark"></i><l:locale name="navMain"/></a></li>
					<li><a href="contacts.jsp"><i class="fa fa-address-book"></i><l:locale name="navContacts"/></a></li>
					<li><a href="/html/controller?command=open_vacancies"><i class="fa fa-list-ul"></i><l:locale name="navVacancies"/></a></li>
					<li>
						<input type="checkbox" name="toggle" class="toggleSubmenu" id="sub_m1">
						<a href="/html/controller?command=open_account"><i class="fa fa-laptop"></i><l:locale name="navAccount"/></a>
						<label for="sub_m1" class="toggleSubmenu"><i class="fa"></i></label>
						<ul>
							<c:set var="session" value="${id}"/>
							<c:choose>
								<c:when test="${session==null}">
									<li><a href="authorization.jsp"><l:locale name="navAuthorization"/></a></li>
									<li><a href="register.jsp"><l:locale name="navRegistration"/></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/html/controller?command=logout"><l:locale name="navLogout"/></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</li>
					<li>
						<input type="checkbox" name="toggle" class="toggleSubmenu" id="sub_m2">
						<a href=""><i class="fa fa-globe"></i><l:locale name="navLanguage"/></a>
						<label for="sub_m2" class="toggleSubmenu"><i class="fa"></i></label>
						<ul>
							<li><a href="/html/controller?command=change_locale&locale=_" name="locale" value="_">Русский</a></li>
							<li><a href="/html/controller?command=change_locale&locale=en_US" name="locale" value="en_US">English</a></li>
						</ul>
					</li>
				</ul>				
			</nav>
		</header>
			<main class="content">
				<%--<div class="title">--%>
					<%--<l:locale name="navVacancies"/>--%>
				<%--</div>--%>

				<jsp:useBean id="daoVacancy" class="by.epam.ft.dao.VacancyDAO" scope="session" />
				<c:set var="resultList" value="${daoVacancy.showAll()}" scope="session"/>

				<table id="vacancyTable" name="thetable">
					<tr>					
						<th><l:locale name="aidvac"/></th>
						<th><l:locale name="ahnvacname"/></th>
						<th><l:locale name="ahnvacdescription"/></th>
						<th><%--<l:locale name="ahnvacdescription"/>--%></th>
					</tr>
					<c:forEach var="row" items="${resultList}">
						<tr>
							<td><p><c:out value="${row.idVacancy}"/></p></td>
							<td><p><c:out value="${row.name}"/></p></td>
							<td><p><c:out value="${row.description}"/></p></td>
							<td>
								<c:choose>
									<c:when test="${hr.equals('false')}">
										<c:set var="flag" value="false"/>

										<c:forEach items="${checkedVacancies}" var="idVac">
											<c:if test="${row.idVacancy == idVac}">
												<c:set var="flag" value="true"/>
											</c:if>
										</c:forEach>

										<c:choose>
											<c:when test="${flag.equals('false')}">
												<a type="button" class="button" href='/html/controller?command=state_vacancy&idVacancy=${row.idVacancy}'><l:locale name="registration"/></a>
											</c:when>
											<c:otherwise>
												<p><l:locale name="alreadyregistered"/></p>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<a type="button" class="button" href='/html/controller?command=delete_vacancy&idVacancy=${row.idVacancy}'><l:locale name="ahdeletevac"/></a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
				<c:choose>
					<c:when test="${hr.equals('true')}">
				<a class="show-btn" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='block';document.getElementById('show-btn').style.display='none'" id="show-btn"><l:locale name="ahaddvac"/></a>
				<div id="envelope" class="envelope">
					<form method="post" action="/html/controller?command=add_vacancy">
						<a class="close-btn" href="javascript:void(0)" onclick = "document.getElementById('envelope').style.display='none';document.getElementById('show-btn').style.display='block'"><l:locale name="ahclose"/></a>
						<p class="txt vacancy-name"><l:locale name="ahnvacname"/></p>
						<input type="text" name="vacName" class="input_fields" required />
						<p class="txt vacancy-name"><l:locale name="ahnvacdescription"/></p>
						<input type="text" name="description" class="input_fields" required />
						<input type="submit" name="send" value="<l:locale name="ahadd"/>" class="confirm_modified">
					</form>
				</div>
					</c:when>
				</c:choose>
				<div class="pic_container">
					<img class="pic" src="../img/vacancy.png" width="50.5%" />
				</div>	
			
			</main>
		
		
		<div class="footer">
			<l:locale name="foot"/>
		</div>
	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>

</html>