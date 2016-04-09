<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html lang="it">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	    <title><tiles:insertAttribute name="title"/></title>
		<meta charset="utf-8" />
		<link href="${pageContext.request.contextPath}/assets/application.min.css" rel="stylesheet" id="bootstrap" />
		<script src="${pageContext.request.contextPath}/assets/application.min.js"></script>
		<meta http-equiv="Expires" content="0"></meta>
		<meta http-equiv="Cache-Control" content="no-cache"></meta>
		<meta http-equiv="Pragma" content="no-cache"></meta>
		<title><tiles:getAsString name="title" /></title>
	</head>
	
	
	<body>
	<% try { %>
		<div class="container">
			<div class="clearfix page-header">
				<tiles:insertAttribute name="header" flush="true" />
			</div>
			<div class="row">
				<div class="span12">
					
					<tiles:insertAttribute name="body"/>
			
				</div>
			</div>
			<div class="footer">
				<tiles:insertAttribute name="footer" flush="true" />
			</div>
		</div>
			<% } catch (Exception e) {
					%>
					Errore di sistema. Si consiglia di attendere qualche minuto e ripetere l'accesso da <a href="http://archivio.pubblica.istruzione.it/istanzeonline/" >Istanze OnLine</a>.
					<%		
				}					
				%>
	</body>
</html>