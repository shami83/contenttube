package wizard.userRegistration;

import wizard.impl.WizardModel;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 22, 2011
 * Time: 10:18:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserWizardModel extends WizardModel {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

}
