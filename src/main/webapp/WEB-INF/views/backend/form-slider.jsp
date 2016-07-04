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
      <h2 class="ui dividing header" style="font-family: Microsoft Yahei">信息类录入</h2>
      <form:form class="ui form" action="form_info" method="post" id="myform" enctype="multipart/form-data"  modelAttribute="form">
        <div class="container">
          <h2 class="ui dividing header" style="font-family: Microsoft Yahei">Slider录入</h2>
          <form class="ui form" action="form_slider" method="post" id="myform" enctype="multipart/form-data">
            <div class="inline field">
              <label>图片</label>
              <input type="file" name="pictures" multiple="multiple">
            </div>
            <div class="inline field">
              <label>活动id</label>
              <input type="tel" name="id" placeholder="">
            </div>
            <div class="inline field">
              <label>静态链接</label>
              <input type="text" name="link" placeholder="">
            </div>
            <button class="ui primary button" type="submit">提交</button>
          </form>
        </div>
      </form:form>
    </div>

</body>
</html>
