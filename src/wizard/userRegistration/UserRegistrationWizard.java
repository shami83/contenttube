package wizard.userRegistration;

import wizard.impl.Wizard;
import wizard.impl.WizardModel;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 22, 2011
 * Time: 10:17:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRegistrationWizard extends Wizard {

    public  WizardModel createModel()
    {
       UserWizardModel model = new  UserWizardModel();
        return model;
    }

     public  void registerSteps()
    {
        register(new InformationStep());
        register(new FinishStep());
    }
}
