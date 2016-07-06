<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Document</title>
  <link rel="stylesheet" type="text/css" href=<spring:url value="/resources/backend/lib/semantic.min.css"/> >
  <link rel="stylesheet" type="text/css" href=<spring:url value="/resources/backend/form.css"/>>
  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
  <script src=<spring:url value="/resources/backend/lib/semantic.min.js"/>></script>
  <script src=<spring:url value="/resources/backend/form.js"/>></script>
</head>
<body>

 	<div class="container">
	<h2 class="ui dividing header" style="font-family: Microsoft Yahei">Slider录入</h2>
    	<form:form class="ui form" action="form_slider" method="post" id="myform" enctype="multipart/form-data"  modelAttribute="form">
	        <div class="inline field">
	          <label>标题</label>
	          <form:input path="title" type="text" name="title" placeholder="" style="width: 500px" maxlength="35" />
	        </div>
            <div class="inline field">
              <label>图片</label>
              <form:input path="pictures" type="file" name="pictures" />
            </div>
            <div class="inline field">
              <label>活动id (If not exist, pls input 0) </label>
              <form:input path="id" type="tel" name="id" placeholder="" />
            </div>
            <div class="inline field">
              <label>静态链接</label>
              <form:input path="link" type="text" name="link" placeholder="" />
            </div>
            <button class="ui primary button" type="submit">提交</button>
    	</form:form>
	</div>


</body>
</html>
