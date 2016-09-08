<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test ="${sessionScope['id'] == null}">
    <c:redirect url="index.jsp" />
</c:if>
<c:if test ="${sessionScope['role'] != 'employee'}">
    <c:redirect url="index.jsp" />
</c:if>
<html>
    <body>
        <c:if test="${Customer!=null}" >
            <form:form method="post" action="add" modelAttribute="Customer">    
                <table >    
                     <tr>    
                         <td>Name : </td>   
                         <td><form:input  path = "name" placeholder = "Name"/></td>  
                     </tr>  
                     <tr>    
                         <td>Mobile Number : </td>   
                         <td><form:input  path = "mobileNumber" /></td>  
                     </tr>     
                     <tr>    
                         <td>Customer Loan Amount :</td>    
                         <td><form:input  path = "loanAmount" placeholder = "Loanamount"/></td>  
                     </tr>   
                     <tr>    
                         <td>Role :</td>    
                         <td><form:select path="role">
                             <form:option value="Employee">Employee</form:option>
                             <form:option value="Customer">Customer</form:option>
                         </form:select>
                     </tr>   
                     <tr>    
                         <td colspan="2"><input type="submit" value="add" /></td>    
                     </tr>    
                 </table>    
            </form:form> 
        </c:if> 
        <c:if test="${Message!=null}" >
            <script language="javaScript" type="text/javascript">
                alert('<c:out value="${Message}" />');
            </script>
        </c:if>	
        <a href="retrive"><button>Retrive</button></a>
        <a href="remove"><button>Remove Employee</button></a>
        <a href="operation"><button>Back</button></a>
        <a href="logout"><button>Logout</button></a>
    </body>
</html>
