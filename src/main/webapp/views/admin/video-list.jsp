<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/taglib.jsp"%>
<a href="<c:url value="/admin/video/add"/>" >Add Video</a>
<table border="1">
  <tr>
    <th>STT</th>
    <th>Video ID</th>
    <th>Poster</th>
    <th>Title</th>
    <th>Description</th>
    <th>View</th>
    <th>Category</th>
    <th>Status</th>
    <th>Action</th>
  </tr>
  <c:forEach items= "${listVideo}" var="video" varStatus="STT" >
    <tr>
      <td>${STT.index+1 }</td>
      <td>${video.videoId}</td>

      <c:if test = "${video.poster.substring(0,5) == 'https'}">
        <c:url value="${video.poster}" var="imgUrl"></c:url>
      </c:if>

      <c:if test = "${video.poster.substring(0,5) != 'https'}">
        <c:url value="/image?frame=${video.poster}" var="imgUrl"></c:url>
      </c:if>

      <td><img height="150" width="200" src= "${imgUrl}" /></td>

      <td>${video.title }</td>
      <td>${video.description }</td>
      <td>${video.views }</td>
      <td>${video.category.categoryname }</td>
      <td>${video.active }</td>

      <td><a href="<c:url value='/admin/video/edit?id=${video.videoId }'/>">Sửa</a>
        | <a href="<c:url value='/admin/video/delete?id=${video.videoId }'/>">Xóa</a></td>
    </tr>
  </c:forEach>
</table>
