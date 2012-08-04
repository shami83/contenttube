package tag.Wizard;

import dao.User;

import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import util.WebConstants;
import util.HTML;
import wizard.impl.WizardModel;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jul 22, 2011
 * Time: 9:19:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class WizardButton implements Tag, Serializable {
    private PageContext pc = null;
	private Tag parent = null;
	private String name = null;
    private Map map=null;
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
            HttpServletRequest req = (HttpServletRequest)pc.getRequest();
            WizardModel model =(WizardModel) req.getAttribute(WebConstants.MODEL);
		    StringWriter writer = appendHtml(new StringWriter(),model);
			pc.getOut().write(writer.toString());


		} catch(IOException e) {
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
    private StringWriter appendHtml(StringWriter writer, WizardModel model)
      {
          writer.append(HTML.openElement("table"));
          writer.append(HTML.openElement("tr"));
           if(model.hasNext())
          {
             writer.append(HTML.openElement("td"));
             writer.append(HTML.openElement("a", getAttribute("next",model)));
             writer.append("Next");
             writer.append(HTML.closeElement("a"));
             writer.append(HTML.closeElement("td"));
          }
           if(model.hasPrevious())
           {
              writer.append(HTML.openElement("td"));
              writer.append(HTML.openElement("a", getAttribute("prev",model)));
              writer.append("Previous");
              writer.append(HTML.closeElement("a"));
              writer.append(HTML.closeElement("td"));
            }

           if(model.isFinish())
           {
              writer.append(HTML.openElement("td"));
              writer.append(HTML.openElement("a", getAttribute("finish",model)));
              writer.append("Finish");
              writer.append(HTML.closeElement("a"));
              writer.append(HTML.closeElement("td"));
            }
             writer.append(HTML.closeElement("tr"));
             writer.append(HTML.closeElement("table"));
         return writer;
      }

    private Map getAttribute(String param,WizardModel model)
    {
      if(map == null)
      {
          map =new HashMap();
      }
        map.clear();
        String link ="#";
        String javaScript = "formSubmit('"+param+"');";
        map.put("href",link);
        map.put("onclick",javaScript);
        return map;
    }

}
