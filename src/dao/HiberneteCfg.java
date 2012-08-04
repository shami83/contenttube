package dao;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.naming.InitialContext;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Dec 14, 2009
 * Time: 8:58:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiberneteCfg {
    SessionFactory   sessionFactory = new Configuration().configure().buildSessionFactory();
    public static Session getSession() {
         Session hsession = null;
        try {
            InitialContext ctx = new InitialContext();
            SessionFactory factory = (SessionFactory)ctx.lookup("java:/hibernate/imagetubeSessionFactory");
             hsession = factory.openSession();

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return hsession;
    }

    public static void main(String args[])
    {
       Session session =  getSession();
        Test test = new Test();
        test.setName("Shamik Mitra");
        session.save(test);
        session.close();
    }
}
