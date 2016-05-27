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
    <div id="root" style="height:100%"></div>
    <script src='<spring:url value="/resources/lib/react.min.js"/>'></script>
    <script src='<spring:url value="/resources/lib/react-dom.min.js"/>'></script>
    <script src='<spring:url value="/resources/lib/zepto.min.js"/>'></script>
    <script>
    window.USERINFO = {
      image : 'http://tp4.sinaimg.cn/1991387607/180/40007947354/1',
      id : 10000,
      name : '大豆梓',
      score: 0
    }
    </script>
    <script src='<spring:url value="/resources/js/index.js"/>'></script>
  </body>
</html>
