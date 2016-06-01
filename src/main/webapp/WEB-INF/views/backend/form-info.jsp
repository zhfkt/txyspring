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
        <div class="inline field">
          <label>活动名称</label>
          <form:input path="title" type="text" name="title" placeholder="" style="width: 500px" maxlength="10" />
        </div>
        <div class="inline field">
          <label>活动类别</label>
          <div class="ui selection dropdown">
            <form:input path="actsubtype" type="hidden" name="actsubtype" />
            <i class="dropdown icon"></i>
            <div class="default text">请选择类别</div>
            <div class="menu">
              <div class="item" data-value="20011">企业宣讲</div>
              <div class="item" data-value="20012">工作实习</div>
              <div class="item" data-value="20013">其他信息</div>
            </div>
          </div>
        </div>        
        
        <div class="inline field">
          <label>开始时间</label>
          <form:input path="startDate" type="date" name="startDate" placeholder="" />
          <form:input path="startTime" type="time" name="startTime" placeholder="" />
        </div>
        <div class="inline field">
          <label>结束时间</label>
          <form:input path="endDate" type="date" name="endDate" placeholder="" />
          <form:input path="endTime" type="time" name="endTime" placeholder="" />
        </div>
        <div class="inline field">
          <label>学校校区</label>
          <div class="ui selection dropdown">
            <form:input path="campus" type="hidden" name="campus" />
            <i class="dropdown icon"></i>
            <div class="default text">请选择校区</div>
           <div class="menu">
              <div class="item" data-value="0">校外</div>
              <div class="item" data-value="10000">同济大学</div>
              <div class="item" data-value="10001">同济大学嘉定校区</div>
              <div class="item" data-value="10002">同济大学四平路校区</div>
              <div class="item" data-value="10003">同济大学彰武路校区</div>
              <div class="item" data-value="10004">同济大学沪西校区</div>
            </div>
          </div>
        </div>
        <div class="inline field">
          <label>活动地点</label>
          <form:input path="location" type="text" name="location" placeholder="" style="width: 500px" maxlength="20" />
        </div>
        <div class="inline field">
          <label>规模人数</label>
          <form:input path="personNum" type="number" name="personNum" placeholder="" />
        </div>
        <div class="inline field">
          <label>主办方&#12288;</label>
          <form:input path="author"  type="text" name="author" placeholder="" />
        </div>
        <div class="inline field">
          <label>岗位简介</label>
          <input type="text" name="info" placeholder="">
        </div>
        <div class="field">
            <label>活动简介</label>
            <form:textarea path="detail" name="detail"></form:textarea>
        </div>
        <div class="inline field">
          <label>封面图片</label>
          <input type="file" name="coverImg">
        </div>        
        <div class="inline field">
          <label>活动图片</label>
          <input type="file" name="pictures" multiple="multiple">
        </div>
        <div class="inline field">
          <label>联系电话</label>
          <form:input path="tel" type="tel" name="tel" placeholder="" />
        </div>
        <div class="inline field">
          <label>联系邮箱</label>
          <form:input path="email" type="email" name="email" placeholder="" />
        </div>
        <div class="inline field">
          <label>QQ&#12288;&#12288;&nbsp;</label>
          <form:input path="qq" type="number" name="qq" placeholder="" />
        </div>
        <button class="ui primary button" type="submit">提交</button>
      </form:form>
    </div>    
    
</body>
</html>
