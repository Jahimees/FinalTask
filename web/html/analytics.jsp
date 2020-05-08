<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType='text/html; charset=UTF-8' %>
<%@ taglib uri="/WEB-INF/MyTag.tld" prefix="l" %>
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
    <link rel="stylesheet" type="text/css" href="../css/modal_contact.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body>
<c:if test="${role==null}">
    <c:redirect url="/html/main.jsp"/>;
</c:if>

<jsp:include page="common/header.jsp"/>
<jsp:include page="common/confirmPopup.jsp"/>

<jsp:useBean id="vacancyDao" class="by.epam.ft.dao.VacancyDAO" scope="session"/>

<main class="content">
    <div class="title">
        <l:locale name="ahtitle"/>
    </div>

    <div id="air" style="margin: 0 auto; width: 100%; height: 400px"></div>
    <%--TODO STYLES --%>
    <div style="display: inline-block; position: relative; left: 30%;">
        <p style="display: inline">С</p>
        <input type="date" id="since_popular_vacancies_date" style="display: inline"/>
        <p style="display: inline">По</p>
        <input style="display: inline" type="date" id="to_popular_vacancies_date"/>
        <input style="display: inline" type="button" value="<l:locale name="aconfirm"/>" id="datesTopVacancies">
    </div>

    <div id="columnchart" style="margin: 0 auto; width: 100%; height: 400px"></div>

    <div style="display: inline-block; position: relative; left: 30%;">
        <p style="display: inline">С</p>
        <input type="date" id="since_hr_statistic_date" style="display: inline"/>
        <p style="display: inline">По</p>
        <input style="display: inline" type="date" id="to_hr_statistic_date"/>
        <input style="display: inline" type="button" value="<l:locale name="aconfirm"/>" id="datesHrStatistics">
    </div>

</main>
<jsp:include page="common/footer.jsp"/>
</body>
</html>

<script type="text/javascript" src="https://www.google.com/jsapi?autoload=
{'modules':[{'name':'visualization','version':'1.1','packages':
['corechart']}]}"></script>
<script>

    google.load("visualization", "1", {packages: ["corechart"]});
    google.setOnLoadCallback(drawChart);

    $("#datesTopVacancies").on("click", function () {
            var sinceDate = $("#since_popular_vacancies_date")[0].value;
            var toDate = $("#to_popular_vacancies_date")[0].value;
            $.ajax({
                url: "/html/controller",
                dataType: "javascript",
                method: "GET",
                data: {
                    command: "filter_analytics",
                    since_date: sinceDate,
                    to_date: toDate,
                    chart_name: "topVacanciesStatistics",
                    noredirect: true
                },
                success: function (data) {
                    drawChart(eval(data.responseText));
                },
                error: function (data) {
                    drawChart(eval(data.responseText));
                }
            });
        }
    );

    function drawChart(dataInfo) {
        var dataView = new google.visualization.DataView(dataInfo);
        var options = {
            title: "<l:locale name="most_popular_vacancies" />",
            is3D: true,
            pieResidueSliceLabel: 'Остальное'
        };
        var chart = new google.visualization.PieChart(document.getElementById('air'));
        chart.draw(dataView, options);
    }

    google.load('visualization', "1", {packages: ['corechart', 'bar']});
    google.setOnLoadCallback(drawColumnChart);

    $("#datesHrStatistics").on("click", function () {
            var sinceDate = $("#since_hr_statistic_date")[0].value;
            var toDate = $("#to_hr_statistic_date")[0].value;
            $.ajax({
                url: "/html/controller",
                dataType: "javascript",
                method: "GET",
                data: {
                    command: "filter_analytics",
                    since_date: sinceDate,
                    to_date: toDate,
                    chart_name: "hrStatisticChart",
                    noredirect: true
                },
                success: function (data) {
                    drawColumnChart(eval(data.responseText));
                },
                error: function (data) {
                    drawColumnChart(eval(data.responseText));
                }
            });
        }
    );

    function drawColumnChart(data) {
        var dataView = new google.visualization.DataView(data);
        var header = {
            title: "<l:locale name="hrs_statistics" />",
            bar: {groupWidth: "50%"}
        };
        var barchart = new google.visualization.ColumnChart(document.getElementById("columnchart"));
        barchart.draw(dataView, header);
    }

    $("#datesTopVacancies").click();
    $("#datesHrStatistics").click();
</script>
