<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <body">
        <div class = "well">
            <font size="20"><marquee behavior="alternate">NET BANKING</marquee></font>
        </div>
        <center>
            <c:if test = "${Address1 != null }">
                <form:form method="post" action="address" modelAttribute="Address1">
                    <tr>    
                        <td>Street : </td>   
                        <td><form:input  path = "street" placeholder = "street"/></td>  
                    </tr>  </br></br>
                    <form:select path="country" class="countries" id="countryId">
                        <form:option value="">Select Country</form:option>
                    </form:select></br></br>
                    <form:select path="state" class="states" id="stateId">
                        <form:option value="">Select State</form:option>
                    </form:select></br></br>
                    <form:select path="city" class="cities" id="cityId">
                        <form:option value="">Select City</form:option>
                    </form:select></br></br>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                    <script src="http://iamrohit.in/lab/js/location.js"></script> 
                    <tr>    
                        <td>Pincode : </td>   
                        <td><form:input  path = "pincode" placeholder = "pincode"/></td>  
                    </tr>  </br></br>
                    <input type="submit" name="addBranch" value="ADD" />
                    
                    </form:form>
                    </c:if>
     <c:if test = "${Address != null }">
     <form:form method="post" action="customerAddress" modelAttribute="Address">
                <tr>    
                   <td>Street : </td>   
                         <td><form:input  path = "street" placeholder = "street"/></td>  
                     </tr>  </br></br>
                     <form:select path="country" class="countries" id="countryId">
                        <form:option value="">Select Country</form:option>
                     </form:select></br></br>
                      <form:select path="state" class="states" id="stateId">
                       <form:option value="">Select State</form:option>
                     </form:select></br></br>
                     <form:select path="city" class="cities" id="cityId">
                        <form:option value="">Select City</form:option>
                    </form:select></br></br>
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
                     <script src="http://iamrohit.in/lab/js/location.js"></script> 
                     <tr>    
                         <td>Pincode : </td>   
                         <td><form:input  path = "pincode" placeholder = "pincode"/></td>  
                     </tr>  </br></br>
                    <input type="submit" name="addBranch" value="ADD" />
                    
                    </form:form>
                    </c:if>
                </center>
</body>
</html>
