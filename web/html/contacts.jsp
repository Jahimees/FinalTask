<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">
	<head>
		<title>IT ROAD. Контакты</title>
		<meta charset="UTF-8"/>		
		<meta name="viewport" content="width=device-width, initial-scale=1">	
		<link rel="stylesheet" href="../css/menu.css" type="text/css">
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"> 					
		<link rel="stylesheet" href="../css/contacts.css" type="text/css">
		<link rel="stylesheet" href="../css/media_contacts.css" type="text/css">
	</head>
	
	<body>
		<jsp:include page="common/header.jsp" />
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

		<jsp:include page="common/footer.jsp" />

	</body>
</html>