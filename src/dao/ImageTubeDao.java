package dao;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jan 18, 2010
 * Time: 9:29:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageTubeDao {
    private static ImageTubeDao dao;

    private ImageTubeDao()
    {

    }
    public static ImageTubeDao getDao()
    {
       if(dao == null)
       {
          dao = new  ImageTubeDao();
       }
        return dao;
    }

    public  Session getSession()
    {
         Session session = HibernateSessionFactory.currentSession();
         return session;
    }

    public void  closeSession()
    {
          HibernateSessionFactory.closeSession();
    }

    public Object findbyId(Integer id,Class clazz)
       {
           Session session = getSession();
           Object retObj = null;
           List objectList = null;
           try {
               Criteria criteria = session.createCriteria(clazz)
                       .add(Restrictions.eq("id", id));
               objectList = criteria.list();

               if (objectList != null && objectList.size() > 0) {
                   retObj = (Object) objectList.get(0);
               }
           }
           catch (HibernateException ex) {
               ex.printStackTrace();
               throw new HibernateException("Error encountered while obtaining object by id",ex);
           }
           HibernateSessionFactory.closeSession();
           return retObj;
       }


     public Object findbyIndex(Integer index,Class clazz)
       {
           Session session = getSession();
           Object retObj = null;
           List objectList = null;
           try {
               Criteria criteria = session.createCriteria(clazz)
                       .add(Restrictions.eq("index", index));
               objectList = criteria.list();

               if (objectList != null && objectList.size() > 0) {
                   retObj = (Object) objectList.get(0);
               }
           }
           catch (HibernateException ex) {
               ex.printStackTrace();
               throw new HibernateException("Error encountered while obtaining object by id",ex);
           }
           HibernateSessionFactory.closeSession();
           return retObj;
       }

     public List getList(Class clazz)
       {
           Session session = getSession();
           Object retObj = null;
           List objectList = null;
           try {
               Criteria criteria = session.createCriteria(clazz);

               objectList = criteria.list();


           }
           catch (HibernateException ex) {
               ex.printStackTrace();
               throw new HibernateException("Error encountered while obtaining object by id",ex);
           }
           HibernateSessionFactory.closeSession();
           return objectList;
       }

     public List getListbyId(Integer id ,Class clazz, Map restrictionList)
       {
           Session session = getSession();
           Object retObj = null;
           List objectList = null;
           try {
               Criteria criteria = session.createCriteria(clazz)
                                .add(Restrictions.eq("id", id));
               if(restrictionList!= null)
               {
                   for(Iterator it=restrictionList.keySet().iterator();it.hasNext();)
                   {
                       String key =(String) it.next();
                       Object value =  restrictionList.get(key);
                       criteria = criteria.add(Restrictions.eq(key,value));
                   }
               }

               objectList = criteria.list();


           }
           catch (HibernateException ex) {
               ex.printStackTrace();
               throw new HibernateException("Error encountered while obtaining object by id",ex);
           }
           HibernateSessionFactory.closeSession();
           return objectList;
       }

       public UserToFriend createfriendRequest(User user,User friend)
       {
           Session session = getSession();
           Transaction tx = session.beginTransaction();

           UserToFriend userToFriend = new UserToFriend();
           userToFriend.setFriend(friend);
           userToFriend.setUser(user);
           userToFriend.setCreateTime(new Date());
           userToFriend.setHasAccepted(new Integer(0).shortValue());

           session.save(userToFriend);
           tx.commit();
           HibernateSessionFactory.closeSession();
           return  userToFriend;
       }


    public List<UserToFriend> getPendingFriendList(User user) throws Exception
    {
         List<UserToFriend> retList =null;
        Session session = getSession();
        Criteria criteria = session.createCriteria(UserToFriend.class)
                               .add(Restrictions.eq("user", user)
                              ).add((Restrictions.eq("hasAccepted", new Integer(0).shortValue())));
         retList = criteria.list();
        return retList;

    }

     public User findbyNameAndPassWord(String emailid,String password)
       {
           Session session = getSession();
           User retObj = null;
           List objectList = null;
           try {
               Criteria criteria = session.createCriteria(User.class)
                       .add(Restrictions.eq("emailId", emailid))
                       .add(Restrictions.eq("passWord", password));
               objectList = criteria.list();

               if (objectList != null && objectList.size() > 0) {
                   retObj = (User) objectList.get(0);
               }
           }
           catch (HibernateException ex) {
               ex.printStackTrace();
               throw new HibernateException("Error encountered while obtaining object by id",ex);
           }
           HibernateSessionFactory.closeSession();
           return retObj;
       }


}
