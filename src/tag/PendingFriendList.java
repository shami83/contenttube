
/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 2, 2010
 * Time: 10:20:13 PM
 * To change this template use File | Settings | File Templates.
 */
package tag;

import dao.User;
import dao.ImageTubeDao;
import dao.UserToFriend;

import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 2, 2010
 * Time: 9:37:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class PendingFriendList implements Tag, Serializable {

	private PageContext pc = null;
	private Tag parent = null;
	 private List<UserToFriend> friendList = null;

	public void setPageContext(PageContext p) {
		pc = p;
	}

	public void setParent(Tag t) {
		parent = t;
	}

	public Tag getParent() {
		return parent;
	}



	public int doStartTag() throws JspException {
		try {
            StringWriter writer = new StringWriter();
            writer.append("<table>");
            HttpServletRequest req = (HttpServletRequest)pc.getRequest();
            HttpSession session = req.getSession();
            User user =(User) session.getAttribute("USER");
            user =(User) ImageTubeDao.getDao().findbyId(1,User.class);
            friendList =ImageTubeDao.getDao().getPendingFriendList(user);
            if(friendList != null && friendList.size()>0)
            {
                for(UserToFriend u2f : friendList)
                {
                     writer = appendHtml(writer,u2f);
                }
            }
            writer.append("</table>");
			pc.getOut().write(writer.toString());


		} catch(Exception e) {
			throw new JspTagException("An IOException occurred.");
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public void release() {
		pc = null;
		parent = null;

	}

    private StringWriter appendHtml(StringWriter writer,UserToFriend u2f )
    {
      String url ="/contentManagement/image?filename=samir.jpg";
      writer.append("<tr>");
      writer.append("<td><img width=\"75\"  src=\""+url+"\"/></td>");
      writer.append("<td>"+u2f.getFriend().getLastName()+" "+u2f.getFriend().getFirstName()+"</td>");
      writer.append("</tr>");
      return  writer;
    }
}