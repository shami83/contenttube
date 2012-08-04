package wizard.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 21, 2011
 * Time: 9:37:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class WizardStep {
    private String PREFIX ="/WEB-INF/views/";
     private String JSP_PATH =null;
    public  void init()
    {
        String name = this.getClass().getName();
        name = name.replace(".","/");
        JSP_PATH =  PREFIX + name+".jsp";
    }
    public String getJspName()
    {
        return  JSP_PATH;
    }

   public abstract void modelToView(HttpServletRequest request,HttpServletResponse response,WizardModel model);
   public abstract void viewToModel(HttpServletRequest request,HttpServletResponse response,WizardModel model);
   public  void finish(HttpServletRequest request,HttpServletResponse response,WizardModel model)
   {

   }
}
