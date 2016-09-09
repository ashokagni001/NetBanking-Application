 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
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
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
         <center>
             <h2>CUSTOMER INFORMATION</h2>
             <table>
                 <br/><br/>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="addTransaction">ADD TRANSACTION</a>
                     </td>
                 </tr>
                 <tr>
                     <td align="center" height="40" width="350">
                         <a href="getTransaction">FETCH TRANSACTION</a>
                     </td>
                 </tr>
                 <tr>
                      <td align="center" height="40" width="350">
                         <a href="notification">NOTIFICATIONS</a>
                     </td>
                 </tr>
                 <tr>
                      <td align="center" height="40" width="350">
                         <a href="viewAllTransaction">FETCH ALL TRANSACTION</a>
                     </td>
                 </tr>
             </table>
         </center>
         <b>Go to main page </b><a href="index.jsp" style="font-sise:18px"> click here</a></br></br>
    </body>
</html>