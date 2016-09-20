<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope['id']== null}">
    <c:redirect url="login" />
</c:if>
<c:if test="${sessionScope['role']!= 'user'}">
    <c:redirect url="approverIndexPage" />
</c:if>
<ul class="nav" id="side-menu">
	<li><a href="getCustomerById"> CUSTOMER DETAIL</a></li>
	<li><a href="viewMiniStatementByCustomerId">VIEW MINI DETAIL</a></li>
	<li><a href="addBeneficiaryAccountForm">ADD NEW BENEFICIARY ACCOUNT</a></li>
        <li><a href="addTransaction">NEW TRANSACTION</a></li>
	<li><a href="viewAllBeneficiaryAccountDetail">VIEW ALL BENEFICIARIES ACCOUNT</a></li>
</ul>
