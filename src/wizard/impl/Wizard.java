package wizard.impl;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.io.IOException;

import util.WebConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 21, 2011
 * Time: 9:36:33 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Wizard extends Action {
    private ArrayList stepList = null;
    private int PREVIOUS_STEP=1;
    private int NEXT_STEP=2;
    private int FINISH_STEP=3;
     private int NEW_STEP=4;
    private void init()
    {
       if(stepList == null)
       {
           stepList = new ArrayList();
           registerSteps();

       }

    }

     public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException
     {
         init();
         int action = getAction(request);
         WizardModel model = (WizardModel)request.getSession().getAttribute(getClass().getName());
         if(model == null)
         {
           model = createModel();
           model.setSteps(stepList);
           model.setFormAction(request.getContextPath() + request.getServletPath());
           request.getSession().setAttribute(getClass().getName(),model);
         }
        WizardStep currentStep = model.getCurrentStep();
         if(action != NEW_STEP)
         {
            currentStep.viewToModel(request,response,model);
         }
         if(action == NEXT_STEP)
         {
             model.doNext();
         }
         else if(action == PREVIOUS_STEP)
         {
             model.doPrevious();
         }
         else if(action == FINISH_STEP)
         {
           model.closePopup();
           request.getSession().removeAttribute(getClass().getName());
         }
         currentStep =  model.getCurrentStep();
         request.setAttribute(WebConstants.MODEL,model);
         currentStep.modelToView(request,response,model);
         if(!response.isCommitted())
         {
             request.getRequestDispatcher(currentStep.getJspName()).forward(request,response);
         }
         return null;
     }

    public abstract void registerSteps();
    public abstract WizardModel createModel();
    public void register(WizardStep step)
    {
        step.init();
        stepList.add(step);
    }

    private int getAction(HttpServletRequest request)
    {
        if(request.getParameter("param") == null)
        {
            return  NEW_STEP;
        }
        else if(request.getParameter("param").equalsIgnoreCase("next"))
        {
           return NEXT_STEP;
        }
        else if(request.getParameter("param").equalsIgnoreCase("prev"))
        {
            return PREVIOUS_STEP;
        }
        else if(request.getParameter("param").equalsIgnoreCase("finish"))
        {
           return FINISH_STEP;
        }
        return  NEW_STEP;
    }

}
