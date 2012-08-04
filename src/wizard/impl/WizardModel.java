package wizard.impl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 21, 2011
 * Time: 9:36:51 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class WizardModel {
    private int currentStep = 0;
    private List stepList;
    private String action = null;
    public void  setSteps(List stepList)
    {
       this.stepList= stepList;
    }

    public WizardStep getCurrentStep()
    {
        return (WizardStep)stepList.get(currentStep);
    }
  public boolean hasNext()
  {
      return (currentStep < (stepList.size()-1));
  }
    public boolean isFinish()
    {
      return (currentStep == (stepList.size()-1));
    }

   public boolean hasPrevious()
  {
      return (currentStep > 0);
  }
   public void doNext()
   {
       if(hasNext())
       {
         currentStep = currentStep+1;
       }
   }

   public void doPrevious()
   {
      if(hasPrevious())
       {
         currentStep = currentStep-1;
       }
   }

    public void closePopup()
    {
        StringBuffer buff = new StringBuffer();
        buff.append("<html><script>self.close()</script></html>");
        buff.toString();
    }
    public  void setFormAction(String action)
    {
        this.action = action;

    }
    public String getFormAction()
    {
        return action;
    }
}
