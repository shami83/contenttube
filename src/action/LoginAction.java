package action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import form.LoginForm;
import dao.ImageTubeDao;
import dao.User;
import util.WebConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 8, 2010
 * Time: 10:13:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction  extends Action {

     public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {
        LoginForm loginForm = (LoginForm)form;
        String email = loginForm.getUserName();
        String password = loginForm.getPassword();
        User user = ImageTubeDao.getDao().findbyNameAndPassWord(email,password);
         if(user!= null)
         {
            request.getSession().setAttribute(WebConstants.USER,user);
            request.getSession().setAttribute(WebConstants.FRIEND,user);
         }
         else
         {
              return mapping.findForward("faliure");
         }
        System.out.println("user = " + user.getFirstName());
         return mapping.findForward("success");
     }
}
