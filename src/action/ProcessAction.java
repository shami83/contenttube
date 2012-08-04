package action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import util.WebConstants;
import util.WebUtil;
import dao.ImageTubeDao;
import dao.User;
import dao.Role;
import dao.UserToFriend;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 9, 2010
 * Time: 9:29:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProcessAction extends Action {

     public ActionForward execute(ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response)
    throws IOException, ServletException
      {

          try
          {
              String friendId = request.getParameter(WebConstants.FRIEND);
              User friend =null;
              List friendList=null;
              if(friendId != null && friendId.length()>0)
              {
                  friend = (User)ImageTubeDao.getDao().findbyId(Integer.parseInt(friendId),User.class);
              }
              else //from loginAction
              {
                 friend = (User)request.getSession().getAttribute(WebConstants.FRIEND);
              }
              request.getSession().setAttribute(WebConstants.FRIEND,friend);
              Map restrictionMap =new HashMap();
              restrictionMap.put("hasAccepted",new Integer(1).shortValue());
              friendList =  ImageTubeDao.getDao().getListbyId(friend.getId(), UserToFriend.class,restrictionMap);
              if(friendList!= null)
              {
                  List acceptedFriendList = new ArrayList();
                  for(Object obj : friendList)
                  {
                     UserToFriend u2f = (UserToFriend)obj;
                     acceptedFriendList.add(u2f.getFriend());
                  }
                  friendList = acceptedFriendList;
              }
              request.getSession().setAttribute(WebConstants.FRIEND_LIST,friendList);
              //get role
              Role role = WebUtil.getRole(request.getSession(),friend);
              List opList = WebUtil.createPermittedOperationList(request.getSession(),role);
             return  mapping.findForward("success");

          }
          catch(Exception ex)
          {
            ex.printStackTrace();
          }
         return null;
      }
}
