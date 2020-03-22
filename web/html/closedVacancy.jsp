<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IT ROAD. Закрытые вакансии</title>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="../css/menu.css" type="text/css"/>
        <link rel="stylesheet" href="../css/media.css" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Cuprum&display=swap"/>
        <link rel="stylesheet" href="../css/vacancy_style.css" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../css/media_contacts.css" type="text/css"/>
        <link rel="stylesheet" type="text/css" href="../css/modal_contact.css"/>
    </head>
    <body>
        <c:if test="${id==null}">
            <c:redirect url="/html/authorization.jsp"/>;
        </c:if>
        <jsp:include page="common/header.jsp"/>
        <main class="content">
        <div class="title">
            <l:locale name="navVacancies"/>
        </div>

        <jsp:useBean id="daoVacancy" class="by.epam.ft.dao.VacancyDAO" scope="session"/>
        <c:set var="resultList" value="${daoVacancy.showVacancies(false)}" scope="session"/>

         <table id="vacancyTable" name="thetable">
             <tr>
                 <th><l:locale name="aidvac"/></th>
                 <th><l:locale name="ahnvacname"/></th>
                 <th><l:locale name="ahnvacdescription"/></th>
                 <th><l:locale name="candidatecount"/></th>
                <c:choose>
                    <c:when test="${hr.equals('true')}">
                        <th></th>
                    </c:when>
                </c:choose>
             </tr>
                <c:forEach var="row" items="${resultList}">
                    <tr>
                        <td><p><c:out value="${row.idVacancy}"/></p></td>
                        <td><p><c:out value="${row.name}"/></p></td>
                        <td><p><c:out value="${row.description}"/></p></td>
                        <td><p><c:out value="${row.candidateCount}"/></p></td>
                        <c:choose>
                            <c:when test="${hr.equals('true')}">
                                <td>
                                    <a type="button" class="button"
                                       href='/html/controller?command=open_vacancy&idVacancy=${row.idVacancy}'><l:locale
                                            name="ahopenvac"/></a>

                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
            </table>
            <div class="pic_container">
                <img class="pic" src="../img/vacancy.png" width="50.5%"/>
            </div>

        </main>

        <jsp:include page="common/footer.jsp"/>

    </body>
</html>
