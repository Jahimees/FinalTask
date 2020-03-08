<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">

	<head>
		<meta charset="UTF-8"/>
		<title>IT ROAD. Аккаунт</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="../css/account_style.css" type="text/css">
		<link rel="stylesheet" href="../css/menu.css" type="text/css">
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap">
		<link rel="stylesheet" type="text/css" href="../css/modal_contact.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	</head>
	
	<body>
		<c:if test="${id==null}">
			<c:redirect url="/html/authorization.jsp"/>;
		</c:if>

		<jsp:include page="common/header.jsp" />

		<main class="content">
			<div class="title">
				<l:locale name="aacc"/>
			</div>

			<div>
				<table class="info">
					<caption><l:locale name="ainfo"/></caption>
						<tr>
							<td>
								<l:locale name="alogin"/>
							</td>
							<td>
								${login}
							</td>
						</tr>
						<tr>
							<td>
								<l:locale name="aname"/>
							</td>
							<td>
								${name}
							</td>
						</tr>
						<tr>
							<td>
								<l:locale name="asur"/>
							</td>
							<td>
								${surname}
							</td>
						</tr>
						<tr>
							<td>
								Email
							</td>
							<td>
								${mail}
							</td>
						</tr>
						<tr>
							<td>
								<l:locale name="abirthday"/>
							</td>
							<td>
								${birthday}
							</td>
						</tr>
				</table>
				<table class="vacancies">
					<caption><l:locale name="avacancies"/></caption>
					<tr>
						<th><l:locale name="aidvac"/></th>
						<th><l:locale name="avac"/></th>
						<th><l:locale name="astatus"/></th>
						<th>HR</th>
						<th width="15%"><l:locale name="aselectiondate"/></th>
						<th><l:locale name="aregistrationdate"/></th>
						<th><l:locale name="arevoke"/></th>
					</tr>
					<c:forEach var="selection" items="${selections}">
						<tr>
							<jsp:useBean id="vacDao" class="by.epam.ft.dao.VacancyDAO"/>
							<jsp:useBean id="accountDao" class="by.epam.ft.dao.AccountDAO"/>
	                        <c:set var="accountHR" value="${accountDao.showByIdUser(selection.idHr, true)}"/>

							<td>${vacDao.showById(selection.idVacancy).idVacancy}</td>
							<td>${vacDao.showById(selection.idVacancy).name}</td>
							<td>
								<c:out value="${selection.status}"/>
							</td>
							<td>
								<c:set var="nameSurname" value="${accountHR.name} ${accountHR.surname}"/>
								<c:choose>
									<c:when test="${accountHR.name==null&&accountHR.surname==null}">
										<l:locale name="anohr"/>
									</c:when>
									<c:otherwise>
										<c:out value="${nameSurname}"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${selection.selectionDate!=null}">
										<c:out value="${selection.selectionDate}"/>
									</c:when>
									<c:otherwise>
										<c:out value="--"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${selection.registrationDate!=null}">
										<c:out value="${selection.registrationDate}"/>
									</c:when>
									<c:otherwise>
										<c:out value="${selection.registrationDate}"/>
										<c:out value="--"/>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<a type="button" class="simple-btn" href='/html/controller?command=revoke_vacancy&idSelection=${selection.idSelection}'><l:locale name="arevoke"/></a>
							</td>
							</c:forEach>
						</tr>
				</table>
				<form action="/html/controller?command=change_password" name = "vform" method="post">
					<div class="password_change">
						<h2><l:locale name="achangepass"/></h2>
						<p><l:locale name="aoldpass"/></p>
						<input type="password" name="oldpass" class="field" required/>
						<p><l:locale name="anewpass"/></p>
						<input type="password" name="password" class="field" required/>
						<p><l:locale name="arepeatpass"/></p>
						<input type="password" name="repeat_password" class="field" required/>
						</p>
						<input type="submit" id="registration" value="<l:locale name="aconfirm"/>" class="confirm_change"/>
					</div>
				</form>
			</div>
		</main>
		
		<jsp:include page="common/footer.jsp" />

	</body>

	<script type="text/javascript" src = "../js/back_security.js"></script>
	<script type="text/javascript" src = "../js/change_password_script.js"></script>
</html>