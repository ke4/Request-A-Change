<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

  <spring:url value="/changerequest/add" var="createCRUrl"/>

  <div class="container">
    <form:form id="crForm" class="form-horizontal table-bordered"
        commandName="changeRequest" action="${createCRUrl }" method="post">
      <br>
      <spring:bind path="title">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="title" class="col-sm-2 control-label">Title</label>
          <div class="col-sm-9">
            <form:input id="title" path="title"
              class="form-control" name="title"
              placeholder="Change Request Title" required="true" autofocus="true"/>
            <form:errors id="error-title" path="title" class="control-label text-warning" />
          </div>
        </div>
      </spring:bind>
      <spring:bind path="summary">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="summary" class="col-sm-2 control-label">Summary</label>
          <div class="col-sm-9">
            <form:textarea id="summary" class="form-control" name="summary"
              placeholder="Change Request Summary" path="summary" />
            <form:errors id="error-summary" path="summary" class="control-label text-warning" />
          </div>
        </div>
      </spring:bind>
      <spring:bind path="detail">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="detail" class="col-sm-2 control-label">Detail</label>
          <div class="col-sm-9">
            <form:textarea id="detail" class="form-control" name="detail"
              placeholder="Change Request Details" path="detail" />
            <form:errors id="error-detail" path="detail" class="control-label text-warning" />
          </div>
        </div>
      </spring:bind>
      <spring:bind path="control">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="control" class="col-sm-2 control-label">Control</label>
          <div class="col-sm-9">
            <form:textarea id="control" class="form-control" name="control"
              placeholder="Change Request Control" path="control" required="true" />
            <form:errors id="error-control" path="control" class="control-label text-warning" />
          </div>
        </div>
      </spring:bind>
      <spring:bind path="customer">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="customer" class="col-sm-2 control-label">Customer</label>
          <div class="col-sm-9">
            <form:input id="customer" path="customer" class="form-control"
                name="customer" placeholder="Name of the customer" required="true" />
            <form:errors id="error-customer" path="customer" class="control-label text-warning" />
          </div>
        </div>
      </spring:bind>
      <spring:bind path="risk">
        <div class="form-group ${status.error ? 'has-error' : ''}">
          <label for="risk" class="col-sm-2 control-label">Risk</label>
          <div class="col-sm-9">
            <form:select path="risk" id="risk" items="${riskItems}" itemValue="value"
              itemLabel="value" class="form-control" required="true"/>
            <form:errors id="error-risk" path="risk" class="control-label text-warning" />
          </div>
        </div>
      </spring:bind>
      <c:choose>
        <c:when test="${ mode eq 'view' }">
          <div class="form-group">
            <label for="state" class="col-sm-2 control-label">State</label>
            <div class="col-sm-9">${changeRequest.state }</div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-10 col-sm-2">
              <spring:url value="/changerequests" var="listUrl"/>
              <a class="btn btn-info float-right" href="${listUrl}">OK</a>
            </div>
          </div>
        </c:when>
        <c:when test="${ mode eq 'add' }">
          <spring:bind path="state">
            <div class="form-group ${status.error ? 'has-error' : ''}">
              <label for="state" class="col-sm-2 control-label">State</label>
              <div class="col-sm-9">
                <form:select path="state" id="state" items="${stateItems}" itemValue="value"
                  itemLabel="value" class="form-control" required="true"/>
                <form:errors id="error-state" path="state" class="control-label text-warning" />
              </div>
            </div>
          </spring:bind>
          <div class="form-group">
            <div class="col-sm-offset-9 col-sm-3">
              <button class="btn btn-info" type="submit">Save As Draft</button>
              <spring:url value="/changerequests" var="listUrl"/>
              <a class="btn btn-info" href="${listUrl}">Cancel</a>
            </div>
          </div>
        </c:when>
      </c:choose>
    </form:form>
  </div>

  <c:if test="${ mode == 'view' }">
    <script type="text/javascript">
      $('#crForm :input').attr('readonly', 'true');
      $('#risk').attr('disabled', 'true');
    </script>
  </c:if>

</body>
</html>