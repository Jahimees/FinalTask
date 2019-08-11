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
		<header>
			<nav class="menu">			
				<input type="checkbox" name="toggle" id="menu" class="toggleMenu">
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
						<a href="#"><i class="fa fa-globe"></i><l:locale name="navLanguage"/></a>
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
					<th><l:locale name="arevoke"/></th>
				</tr>
				<c:forEach var="selection" items="${selections}">
					<tr>
						<c:set var="accountHR" value="${accountDao.showByIdUser(selection.idHr, true)}"/>
						<jsp:useBean id="vacDao" class="by.epam.ft.dao.VacancyDAO"/>
						<jsp:useBean id="accountDao" class="by.epam.ft.dao.AccountDAO"/>

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
		
		<div class="footer">
			<l:locale name="foot"/>
		</div>

	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>
	<script type="text/javascript" src = "../js/change_password_script.js"></script>

</html>