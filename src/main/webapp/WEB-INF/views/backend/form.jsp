<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
      <h2 class="ui dividing header" style="font-family: Microsoft Yahei">活动类录入</h2>
      <form class="ui form" action="http://www.test.com/testForm" method="post" id="myform" enctype="multipart/form-data">
        <div class="inline field">
          <label>活动名称</label>
          <input type="text" name="title" placeholder="" style="width: 500px" maxlength="10">
        </div>
        <div class="inline field">
          <label>活动类别</label>
          <div class="ui selection dropdown">
            <input type="hidden" name="actsubtype">
            <i class="dropdown icon"></i>
            <div class="default text">请选择类别</div>
            <div class="menu">
              <div class="item" data-value="10011">讲座报告</div>
              <div class="item" data-value="10012">文艺演出</div>
              <div class="item" data-value="10013">体育赛事</div>
              <div class="item" data-value="10014">社团社交</div>
              <div class="item" data-value="10015">竞赛培训</div>
              <div class="item" data-value="10016">其他活动</div>
            </div>
          </div>
        </div>        
        
        <div class="inline field">
          <label>开始时间</label>
          <input type="date" name="startDate" placeholder="">
          <input type="time" name="startTime" placeholder="">
        </div>
        <div class="inline field">
          <label>结束时间</label>
          <input type="date" name="endDate" placeholder="">
          <input type="time" name="endTime" placeholder="">
        </div>
        <div class="inline field">
          <label>学校校区</label>
          <div class="ui selection dropdown">
            <input type="hidden" name="campus">
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
          <input type="text" name="location" placeholder="" style="width: 500px" maxlength="20">
        </div>
        <div class="inline field">
          <label>规模人数</label>
          <input type="number" name="personNum" placeholder="">
        </div>
        <div class="inline field">
          <label>主办方&#12288;</label>
          <input type="text" name="author" placeholder="">
        </div>
        <div class="inline fields">
            <label for="fruit">是否需要预约</label>
            <div class="field">
              <div class="ui radio checkbox">
                <input type="radio" name="needOrder" checked="checked" value="0" tabindex="0" class="hidden">
                <label>不需要</label>
              </div>
            </div>
            <div class="field">
              <div class="ui radio checkbox">
                <input type="radio" name="needOrder" tabindex="0" value="1" class="hidden">
                <label>需要</label>
              </div>
            </div>
        </div>
        <div class="field">
            <label>活动简介</label>
            <textarea name="detail"></textarea>
        </div>
        <div class="inline field">
          <label>活动图片</label>
          <input type="file" name="pictures" multiple="multiple">
        </div>
        <div class="inline field">
          <label>联系电话</label>
          <input type="tel" name="tel" placeholder="">
        </div>
        <div class="inline field">
          <label>联系邮箱</label>
          <input type="email" name="email" placeholder="">
        </div>
        <div class="inline field">
          <label>QQ&#12288;&#12288;&nbsp;</label>
          <input type="number" name="qq" placeholder="">
        </div>
        <button class="ui primary button" type="submit">提交</button>
      </form>
    </div>
</body>
</html>
