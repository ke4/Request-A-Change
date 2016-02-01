<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of the current Change Requests</title>
<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<link href="${bootstrap}" rel="stylesheet" />
<spring:url value="/resources/js/util.js" var="utiljs" />
<script src="${utiljs}" type="text/javascript"></script>
</head>
<body>
  <div class="page-header text-center">
    <h1>List of Change Requests</h1>
  </div>
  <spring:url value="/changerequest/add" var="urlAddChangeRequest" />
  <div class="container">
    <div class="content">
    <div>
      <button type="button" class="btn btn-primary"
        onclick="location.href='${urlAddChangeRequest}'"
      >Add Request +</button>
    </div>
    <div id="cr-list" class="page-content">
      <c:choose>
        <c:when test="${empty changeRequests}">
          <p class="text-center">
            <spring:message code="label.cr.list.empty" />
          </p>
        </c:when>
        <c:otherwise>

          <table class="table table-striped">
            <thead>
              <tr>
                <th>#ID</th>
                <th>Title</th>
                <th>Risk</th>
                <th>State</th>
                <th>Action</th>
              </tr>
            </thead>

            <c:forEach var="cr" items="${changeRequests}">
              <tr>
                <td>${cr.id}</td>
                <td>${cr.title}</td>
                <td>${cr.risk}</td>
                <td>${cr.state}</td>
                <td>
                  <spring:url value="/changerequest/${cr.id}" var="crUrl"/>
                  <spring:url value="/changerequest/${cr.id}/update" var="updateUrl"/>
                  <spring:url value="/changerequest/${cr.id}/delete" var="deleteUrl"/>

                  <button class="btn btn-info" onclick="location.href='${crUrl}'">Details</button>
                  <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                  <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
              </tr>
            </c:forEach>
          </table>
        </c:otherwise>
      </c:choose>
      </div>
    </div>
  </div>

</body>
</html>