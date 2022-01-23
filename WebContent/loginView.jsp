<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>

      <h3>Login Page</h3>
      <p style="color: red;">${strError}</p>


      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table border="0">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="userName"/> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password"/> </td>
            </tr>
            <tr>
               <td>Remember me</td>
               <input type="hidden" name="rememberMe" value= "N" />
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
     
   </body>
</html>
 