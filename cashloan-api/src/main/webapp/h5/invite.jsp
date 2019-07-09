<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%

	String invitationCode= request.getParameter("invitationCode");
	String channelCode= request.getParameter("channelCode");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>轻松借款</title>
    <meta name="keywords" content="贷款,小额借钱,借贷,贷款app,急用钱,短期快速放贷,极速借款借钱,小额贷款">
    <meta name="description" content="专注于为个人提供正规小额贷款、无抵押贷款、个人贷款、闪电借钱等服务">
    <script src="/static/js/flexable.js"></script>
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="#7CD88E">
    <link rel="stylesheet" href="/static/css/style.css"/>
</head>

<body onload="loadH5Vue()">
</body>
</html>

<script>
    function loadH5Vue() {
        <%if(invitationCode != null&&!invitationCode.equals("")&&!invitationCode.equals("null")){ %>
        window.location.href="/h5_vue/index.html?invitationCode=<%=invitationCode%>&channelCode=<%=channelCode%>"
        <%} %>
        <%if(invitationCode == null || invitationCode.equals("") || invitationCode.equals("null")){ %>
        window.location.href="/h5_vue/index.html?channelCode=<%=channelCode%>"
        <%} %>
    }
</script>