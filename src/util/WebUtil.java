package util;

import dao.Operation;
import dao.Role;
import dao.User;
import dao.ImageTubeDao;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 8, 2010
 * Time: 10:00:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebUtil {

    public static boolean isOperationPermitted(Operation op, HttpSession session)
    {
         List permList =(List) session.getAttribute(WebConstants.PERMITTED_OPERATION_LIST);
         return  permList.contains(op);
    }

    public static List createPermittedOperationList(HttpSession session, Role role)
    {
        List opList =(List) session.getServletContext().getAttribute(WebConstants.OPERATION_LIST);
        List permList =new ArrayList();
        for(Iterator it=opList.iterator();it.hasNext();)
        {
              Operation op =(Operation)it.next();
              if((role.getCumulativeBitmap().intValue() & op.getBitmap().intValue()) == op.getBitmap().intValue())
              {
                 permList.add(op);
              }
        }
        session.setAttribute(WebConstants.PERMITTED_OPERATION_LIST,permList);
        return permList;
    }

    public static Role getRole(HttpSession session,User friend)
    {
        Role role = null;
        User user =(User) session.getAttribute(WebConstants.USER);
        List frendList = (List) session.getAttribute(WebConstants.FRIEND_LIST) ;
        if(user.getAdmin().intValue() == 1)
        {
           role = (Role)ImageTubeDao.getDao().findbyIndex(WebConstants.ADMIN_ROLE,Role.class);
        }
       else if(user.equals(friend))
        {
           role = (Role)ImageTubeDao.getDao().findbyIndex(WebConstants.OWNER_ROLE,Role.class);
        }
        else if(frendList!=null && frendList.contains(user))
        {
            role = (Role)ImageTubeDao.getDao().findbyIndex(WebConstants.FRIEND_ROLE,Role.class);
        }
        else
        {
           role = (Role)ImageTubeDao.getDao().findbyIndex(WebConstants.GUEST_ROLE,Role.class);
        }
        session.setAttribute(WebConstants.ROLE,role);
        return role;
    }
}
