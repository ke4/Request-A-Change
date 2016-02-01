<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GCLP - Request a Change Application</title>
<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<link href="${bootstrap}" rel="stylesheet" />
<spring:url value="/resources/js/jquery-2.1.4.min.js" var="jqueryjs" />
<script src="${jqueryjs}" type="text/javascript"></script>
</head>
<body>
  <div class="page-header text-center">
    <h1>Change Request Details</h1>
  </div>

  <div class="container">
    <form id="crForm" class="form-horizontal table-bordered">
      <br>
      <div class="form-group">
        <label for="title" class="col-sm-2 control-label">Title</label>
        <div class="col-sm-9">
          <input id="title"
            class="form-control" name="title" value="${changeRequest.title}"
            placeholder="Change Request Title" required autofocus/>
        </div>
      </div>
      <div class="form-group">
        <label for="summary" class="col-sm-2 control-label">Summary</label>
        <div class="col-sm-9">
          <textarea id="summary" class="form-control" name="summary"
            placeholder="Change Request Summary">${changeRequest.summary}
          </textarea>
        </div>
      </div>
      <div class="form-group">
        <label for="detail" class="col-sm-2 control-label">Detail</label>
        <div class="col-sm-9">
          <textarea id="detail" class="form-control" name="detail"
            placeholder="Change Request Details">${changeRequest.detail}</textarea>
        </div>
      </div>
      <div class="form-group">
        <label for="control" class="col-sm-2 control-label">Control</label>
        <div class="col-sm-9">
          <textarea id="control" class="form-control" name="control"
            placeholder="Change Request Control" required>${changeRequest.control}</textarea>
        </div>
      </div>
      <div class="form-group">
        <label for="customer" class="col-sm-2 control-label">Customer</label>
        <div class="col-sm-9">
          <input id="customer"
            class="form-control" name="customer" value="${changeRequest.customer}"
            placeholder="Name of the customer" required/>
        </div>
      </div>
      <div class="form-group">
        <label for="risk" class="col-sm-2 control-label">Risk</label>
        <div class="col-sm-9">
          <select id="risk"
            class="form-control" name="risk" required>
            <option>Low</option>
            <option>Medium</option>
            <option>High</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="state" class="col-sm-2 control-label">State</label>
        <div class="col-sm-9">
          ${changeRequest.state }
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-10 col-sm-2">
          <spring:url value="/changerequests" var="listUrl"/>
          <a class="btn btn-info float-right" href="${listUrl}">OK</a>
        </div>
      </div>
    </form>
  </div>

  <c:if test="${ mode == 'view' }">
    <script type="text/javascript">
      $('#crForm :input').attr('readonly', 'true');
      $('#risk').attr('disabled', 'true');
    </script>
  </c:if>

</body>
</html>