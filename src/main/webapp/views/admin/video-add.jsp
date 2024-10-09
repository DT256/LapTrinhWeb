<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<form action="<c:url value = "/admin/video/insert"/>" method="post" enctype="multipart/form-data">
  <label for="title">Title:</label><br>
  <input type="text" id="title" name="title"><br>

  <label for="poster">Link Poster:</label><br>
  <input type="text" id="poster" name="poster"><br>

  <label for="poster1">Upload Poster:</label><br>
  <input type="file" id="poster1" name="poster1"><br>

  <label for="poster1">Choose Category:</label><br>
  <select name="categoryid" id="categoryid">
    <c:forEach items= "${listCate}" var="cate">
      <option value = ${cate.categoryid}>${cate.categoryname}</option>
    </c:forEach>
  </select><br>

  <label for="status">Status:</label><br>

  <input type="radio" id="statuson" name="active" value="1">
  <label for="fname">Hoạt động</label><br>
  <input type="radio" id="statusoff" name="active" value="0">
  <label for="fname">Không Hoạt động</label><br>

  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description"><br>

  <hr>
  <input type="submit" value="Insert">
</form>
