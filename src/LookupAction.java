

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.Test;
import dao.HiberneteCfg;
import dao.HibernateSessionFactory;
import dao.User;

public class LookupAction extends Action {

  protected Double getQuote(String symbol) {

    if ( symbol.equalsIgnoreCase("SUNW") ) {

      return new Double(25.00);
    }
    return null;
  }

  public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException {

    Double price = null;

    // Default target to success
    String target = new String("success");

           if ( form != null ) {

             // Use the LookupForm to get the request parameters
             LookupForm LookupForm = (LookupForm)form;

      String symbol = LookupForm.getSymbol();

      price = getQuote(symbol);

      /*   Session session = HibernateSessionFactory.currentSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setFirstName("Shamik");
        user.setLastName("Mitra");
        user.setImageUrl("/images/shamik.jpg");
        user.setAddress("1, Nivedita Lane Kolkata -3");
        user.setCity("Kolkata");
        user.setAdmin(new Integer(1).shortValue());
        user.setEmailId("shamik.mitra@gmail.com");
        user.setPassWord("aaaaaa");
        user.setCreateTime(new Date());

        session.save(user);
        tx.commit();

        HibernateSessionFactory.closeSession(); */
    }

    // Set the target to failure
    if ( price == null ) {

      target = new String("failure");
    }
    else {

      request.setAttribute("PRICE", price);
    }
    // Forward to the appropriate View
    return (mapping.findForward(target));
  }
}
