package wizard.userRegistration;

import wizard.impl.WizardStep;
import wizard.impl.WizardModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 22, 2011
 * Time: 10:19:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class InformationStep extends WizardStep {
    public void modelToView(HttpServletRequest request, HttpServletResponse response, WizardModel model)
    {

    }
   public  void viewToModel(HttpServletRequest request,HttpServletResponse response,WizardModel model)
   {
       wizard.userRegistration.UserWizardModel uModel =(UserWizardModel)model;
       uModel.setUserName(request.getParameter("user"));
   }
}
