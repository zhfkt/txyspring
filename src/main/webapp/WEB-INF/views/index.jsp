<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
  <head>
    <title>同萌科技</title>
    <meta charset="utf-8">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta name="applicable-device" content="mobile">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
  </head>
  <body>
    
    <div align="center">
		<h1>UserID: ${UserID}</h1>
		<h2>oriId: ${oriId}</h2>
		<img src='<spring:url value="/resources/images/gorilla.jpg"/>' alt="gorilla" />
	</div>
    
    
    
    
    
    <!-- 
	    <script src="//cdn.bootcss.com/zepto/1.1.6/zepto.min.js"></script>
	    <script src="/static/bundle.js"></script>
     -->
  </body>
</html>
