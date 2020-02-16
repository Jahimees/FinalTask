<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l"%>
<!DOCTYPE html>
<html lang="ru">

	<head>
		<meta charset="UTF-8"/>
		<title>IT ROAD</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="../css/main_style.css" type="text/css">		
		<link rel="stylesheet" href="../css/menu.css" type="text/css">
		<link rel="stylesheet" href="../css/media.css" type="text/css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"> 
	</head>
	
	<body>
		<img class="hat" src="../img/head.svg" width=95%/>
		<jsp:include page="common/header.jsp" />

		<main class="content">

			<div class="title">
				<h1><l:locale name="whoAreWe"/></h1>
			</div>
			<div class="txt">
				<p><l:locale name="mtxt1"/></p>
			</div>
			<div class="pic_container">
				<img class="pic" src="../img/developers.jpg" width="33%"/>
				<img class="pic" src="../img/testers.jpg" width="30.5%" />
				<img class="pic" src="../img/analytics.jpg" width="32.5%"/>				
			</div>			
			<div class="txt">				
				<p><l:locale name="mtxt2"/></p>
				<p><l:locale name="mtxt3"/></p>
				<div class="pic_container">
					<img class="ico" src="../img/projects.png" width="10%"/>				
					<img class="ico" src="../img/communication.png" width="10%"/>				
					<img class="ico" src="../img/technologies.png" width="10%"/>
					<img class="ico" src="../img/web.png" width="10%"/>
				</div>
				<hr>
			</div>
			
			<div class="title">
				<h1><l:locale name="whatDoWeDo"/></h1>
			</div>
			<div class="txt"> 
				<p><l:locale name="mtxt4"/></p>
				<div class="pic_container">
					<img class="pic" src="../img/tech1.jpg" width="33%"/>				
					<img class="pic" src="../img/tech2.jpg" width="32.5%"/>
					<img class="pic" src="../img/tech3.jpg" width="31.5%"/>				
				</div>
				<hr>
			</div>
			
			<div class="title">
				<h1><l:locale name="ForWhomAreWeDoing"/></h1>
			</div>
			<div class="txt"> 
				<p><l:locale name="mtxt5"/></p>
				<div class="pic_container">
					<img class="pic" src="../img/hitech1.jpg" width="33%"/>				
					<img class="pic" src="../img/hitech2.jpg" width="33%"/>
					<img class="pic" src="../img/hitech3.jpg" width="28.8%"/>				
				</div>
				<hr>
			</div>
		</main>

		<jsp:include page="common/footer.jsp" />

		<c:if test="${wrongPass=='true'}">
			<script>
				alert("Пароль не был изменен!");
			</script>
			${wrongPass=null}
		</c:if>
		<c:if test="${wrongPass=='false'}">
			<script>
				alert("Пароль успешно изменен!");
			</script>
			${wrongPass=null}
		</c:if>
	</body>
	<script type="text/javascript" src = "../js/back_security.js"></script>

</html>