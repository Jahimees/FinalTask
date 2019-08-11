<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">
	<head>
		<meta charset="UTF-8"/>
		<title>IT ROAD. Авторизация</title>
		<link rel="stylesheet" href="../css/register_st1yle.css" type="text/css"/>
		<link rel="stylesheet" href="../css/menu.css" type="text/css"> 
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"> 	
	</head>

	<body>

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
	<form class="content" action="/html/controller?command=login" method="post">
			<div class="title">
				<l:locale name="auth"/>
			</div>

			<div class="registration">

				<div class="field_names">
					<h1><l:locale name="alogin"/></h1>
				</div>
				<input name="login" class="field" placeholder="ExampleLogin" required/>
				<div id = "login_error"></div>
				
				<div class="field_names">
					<h1><l:locale name="pass"/></h1>
				</div>
				<input class="field" name="password" type="password" placeholder="ExamplePassword1999" required/>
				<c:set var ="error" value="${errorMessage}"/>
				<c:if test="${error.length() > 0}">
					<p style="color: red; text-align: center; margin: 20px auto; font-size: 20px;">${error}</p>
				</c:if>
				<input name="authorization" id="authorization" value="<l:locale name="enter"/>" class="button" type="submit"/>
			</div>
	</form>
		
		<div class="footer">
			<l:locale name="foot"/>
		</div>

		
	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>
</html>