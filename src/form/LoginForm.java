package form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 8, 2010
 * Time: 10:15:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginForm  extends ActionForm {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
