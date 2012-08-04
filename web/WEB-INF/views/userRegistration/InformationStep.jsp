<%@ taglib uri="/WEB-INF/tlds/web.tld" prefix="web" %>
<%@ page import="util.WebConstants" %>
<%@ page import="wizard.userRegistration.UserWizardModel" %>
<%@page language="java" %>
<%
   UserWizardModel model =(UserWizardModel)request.getAttribute(WebConstants.MODEL);
%>
<html>
<head>
 <script type="text/javascript">
     function formSubmit(param)
     {
         document.main.param.value=param;
         document.forms['main'].submit();
     }
 </script>
</head>
<form name="main" method="post" action="userRegistration.do">
    <table>
        <tr>
            <td><input type="text" name="user" value="<%=model.getUserName()!=null?model.getUserName():""%>"/></td>
            <input type=hidden name="param">
        </tr>
        <tr>
             <td><web:wizardButton/></td>
        </tr>
    </table>
</form>
</html>