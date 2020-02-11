<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">
	<head>
		<title>IT ROAD. Контакты</title>
		<meta charset="UTF-8"/>		
		<meta name="viewport" content="width=device-width, initial-scale=1">	
		<link rel="stylesheet" href="../css/menu1.css" type="text/css">
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"> 					
		<link rel="stylesheet" href="../css/contacts.css" type="text/css">
		<link rel="stylesheet" href="../css/media_contacts.css" type="text/css">
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
			<main class="content">			
				<div class="title">
					<l:locale name="contacts"/>
				</div>
				
				<table>
					<tr>
						<td>
							<p><l:locale name="phone"/></p>
						</td>
						<td>
							<p>+375-NN-NN-NN-NNN</p>
						</td>
					</tr>
					<tr>
						<td>
							<p><l:locale name="phone2"/></p>
						</td>
						<td>
							<p>+375-NN-NN-NN-NNN</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>Skype</p>
						</td>
						<td>
							<p>SkypeId</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>vkgroup</p>
						</td>
						<td>
							<p>https://vk.com/exampleLink</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>Instagram</p>
						</td>
						<td>
							<p>@exampleInstId</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>Email</p>
						</td>
						<td>
							<p>ExampleAdress@mail.ru</p>							
						</td>
					</tr>
					<tr>
						<td>
							<p>Email-2</p>
						</td>
						<td>							
							<p>ExampleAdress2@gmail.com</p>
						</td>
					</tr>
				</table>
				<div class="pic_container">
					<img class="pic" src="../img/contacts.png" width="50.5%"/>		
				</div>	
			
			</main>
		
		
		<div class="footer">
			<l:locale name="foot"/>
		</div>
	</body>
</html>