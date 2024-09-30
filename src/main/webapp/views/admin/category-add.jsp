<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<form action="<c:url value = "/admin/category/insert"/>" method="post" enctype="multipart/form-data">
  <label for="fname">Category name:</label><br>
  <input type="text" id="fname" name="categoryname"><br>
  <label for="image">Link Image:</label><br>
  <input type="text" id="image" name="image"><br>

  <label for="image1">Upload Image:</label><br>
  <input type="file" id="image1" name="image1"><br>

  <label for="status">Status:</label><br>

  <input type="radio" id="statuson" name="status" value="1">
  <label for="fname">Hoạt động</label><br>
  <input type="radio" id="statusoff" name="status" value="0">
  <label for="fname">Không Hoạt động</label>
  <hr>

  <input type="submit" value="Insert">
</form>
