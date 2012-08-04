package dao;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class HibernateAppListener implements ServletContextListener {

    /* Application Startup Event */
    public void contextInitialized(ServletContextEvent ce) {
        System.out.println("init");

        try {
            System.out.println("initialized");

            Class.forName("dao.HibernateSessionFactory").newInstance();
            System.out.println("In HibernateAppListener, Class.forName for tomcatJndi.HibernateUtil successful");
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
