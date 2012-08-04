package tag;

import dao.UserToFriend;
import dao.User;
import dao.ImageTubeDao;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.StringWriter;
import java.io.Serializable;

import util.WebConstants;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 9, 2011
 * Time: 12:35:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class FriendListTag implements Tag, Serializable {
    private PageContext pc = null;
	private Tag parent = null;
	 private List<User> friendList = null;

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
            friendList =(List)session.getAttribute(WebConstants.FRIEND_LIST);
            if(friendList != null && friendList.size()>0)
            {
                for(User friend : friendList)
                {
                     writer = appendHtml(writer,friend);
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

    private StringWriter appendHtml(StringWriter writer,User friend )
    {
      String url ="/contentManagement/image?filename="+friend.getImageUrl();
      writer.append("<td><a href=\"processAction.do?"+WebConstants.FRIEND+"="+friend.getId()+"\"><img width=\"100\"  src=\""+url+"\"/><br/>"+friend.getFirstName()+"</a></td>");
      return  writer;
    }
}
