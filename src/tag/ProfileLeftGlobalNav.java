package tag;

import util.WebConstants;
import util.HTML;

import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import dao.User;
import dao.Operation;

/**
 * Created by IntelliJ IDEA.
 * User: BHAI2011
 * Date: Feb 2, 2011
 * Time: 9:42:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileLeftGlobalNav implements Tag, Serializable {
    private PageContext pc = null;
	private Tag parent = null;
    public void setPageContext(PageContext pageContext) {
       pc = pageContext;
    }

    public void setParent(Tag tag) {
       parent = tag;
    }

    public Tag getParent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int doStartTag() throws JspException {
        try
        {
            HttpServletRequest req = (HttpServletRequest)pc.getRequest();
            HttpSession session = req.getSession();
            User friend =(User)session.getAttribute(WebConstants.FRIEND);
            List opList = (List)session.getAttribute(WebConstants.PERMITTED_OPERATION_LIST);
            StringWriter writer =appendHtml(new StringWriter(),opList,friend);
            pc.getOut().write(writer.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
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

    private StringWriter  appendHtml(StringWriter writer,List list,User friend)
    {
        String url ="/contentManagement/image?filename="+friend.getImageUrl();
        Map image = new HashMap();
        Map link = new HashMap();
        image.put("width","100");
        image.put("src",url);
       writer.append(HTML.openElement("table",null)) ;
       writer.append(HTML.openElement("tr",null)) ;
       writer.append(HTML.openElement("td",null)) ;
       writer.append(HTML.openElement("img",image)) ;
       writer.append(HTML.closeElement("img")) ;
       writer.append(HTML.closeElement("td")) ;
       writer.append(HTML.closeElement("tr")) ;
       for(Iterator it=list.iterator();it.hasNext();)
       {
          Operation op =(Operation)it.next();
          link.put("href","");
          writer.append(HTML.openElement("tr",null)) ;
          writer.append(HTML.openElement("td",null)) ;
          writer.append(HTML.openElement("a",link)) ;
          writer.append(op.getDisplayName());
          writer.append(HTML.closeElement("a")) ;
          writer.append(HTML.closeElement("td")) ;
          writer.append(HTML.closeElement("tr")) ;
          link.clear();
       }
        writer.append(HTML.closeElement("table")) ;
        return writer;
    }
}
