package util;

import java.util.Map;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: BHAI2011
 * Date: Feb 1, 2011
 * Time: 9:20:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class HTML {

    public static String openElement(String element, Map attribueList)
    {
       StringBuffer buff = new StringBuffer("<"+element);
        if (attribueList != null) {
            for (Iterator it = attribueList.keySet().iterator(); it.hasNext();) {
                Object key = it.next();
                Object value = attribueList.get(key);
                buff.append(" " + key + " = " + "\"" + value + "\"");
            }
        }
         buff.append(">");
        return buff.toString();
    }

    public static String closeElement(String element)
    {
        StringBuffer buff = new StringBuffer("</"+element+">");
        return buff.toString();
    }


}
