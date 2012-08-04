<%@ page language="Java" %>
<%@ taglib
  uri="/WEB-INF/struts-html.tld"
  prefix="html" %>

<html>
  <head>
    <title>Wrox Struts Application</title>
  </head>

  <body>
    <table width="500" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td height="68" width="48%">
          
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>

    <html:form action="login"
     >

      <table width="45%" border="0">
        <tr>
          <td>EmailId:</td>
          <td><html:text property="userName" /></td>
        </tr>
         <tr>
          <td>Password:</td>
          <td><html:password property="password" /></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><html:submit /></td>
        </tr>
      </table>
    </html:form>

  </body>
</html>

