<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="resource/css/bootstrap.css">
        <script src="resource/js/bootstrap.js"></script>
        <script src="resource/js/bootstrap1.js"></script>
        <style type="text/css">
            .well {
                background-color:  #8ddfe1 ;
            }
        </style>
    </head>
    <body>
         <c:if test="${message != null}">
            <script type="text/javascript">
                alert('CLICK OK THE PAGE WILL BE REFRESHED...' + "<c:out value='${message}'/>");
                windows.location.reload();
            </script>
        </c:if>
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
         <center>
             <h2>CUSTOMER INFORMATION</h2>
             <table>
                 <br/><br/>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="CustomerRegistration">CUSTOMER REGISTRATION</a>
                     </td>
                 </tr>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="GetCustomer">FETCH CUSTOMER</a>
                     </td>
                 </tr>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="getMiniStatementByCustomerId">CUTOMER MINI STATEMENT</a>
                     </td>
                 </tr>
             </table>
         </center>
         <b>Go to main page </b><a href="index.jsp" style="font-sise:18px"> click here</a></br></br>
    </body>
</html>