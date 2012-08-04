package util;

import dao.ImageTubeDao;
import dao.Operation;
import dao.Role;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 8, 2010
 * Time: 9:49:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationListener implements ServletContextListener {
     public void contextInitialized(ServletContextEvent ce) {
        System.out.println("init");

        try {
           List operationList = ImageTubeDao.getDao().getList(Operation.class);
           List roleList = ImageTubeDao.getDao().getList(Role.class);
           Class.forName("util.WebConstants");
           ce.getServletContext().setAttribute(WebConstants.OPERATION_LIST,operationList);
           ce.getServletContext().setAttribute(WebConstants.ROLE_LIST,roleList);  
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("In HibernateAppListener, Class.forName for tomcatJndi.HibernateUtil throws Exception");
        }
    }

    /* Application Shutdown	Event */
    public void contextDestroyed(ServletContextEvent ce) {
    }
}
