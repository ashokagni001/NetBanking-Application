<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<h2><fmt:message key="home.heading"/></h2>
<p><fmt:message key="home.message"/></p>

<ul class="glassList">
<li>
        <a href="<c:url value='/addBranch'/>">Add Branch</a>
    </li>
    <li>
        <a href="<c:url value='/addAccount'/>">Add Account</a>
    </li>
     <li>
        <a href="<c:url value='/viewBranches'/>">View All Branches</a>
    </li>
    <li>
        <a href="<c:url value='/viewAccountByBranch'/>">View Account By Branch</a>
    </li>
    <li>
        <a href="<c:url value='/addBeneficiaryAccount'/>">Add Beneficiary</a>
    </li>
    <li>
        <a href="<c:url value='/addTransaction'/>">Add Transaction</a>
    </li>
    <li>
        <a href="<c:url value='/transactionNotifications'/>">Transaction Notifications</a>
    </li>
    <li>
        <a href="<c:url value='/viewAllBeneficiaryAccountDetail'/>">View All Beneficiaries</a>
    </li>
	<li>
	    <a href="<c:url value='/viewMiniStatementByCustomerId'/>">View Mini Statement</a>
	</li>
	<li>
	    <a href="<c:url value='/viewAllTransaction'/>">View All Transaction</a></li>
    <li>
        <a href="<c:url value='/beneficiaryNotifications'/>">View Beneficiary Notifications</a>
    </li>
    <li>
        <a href="<c:url value='/userform'/>"><fmt:message key="menu.user"/></a>
    </li>
    <li>
        <a href="<c:url value='/fileupload'/>"><fmt:message key="menu.selectFile"/></a>
    </li>
</ul>
</body>
