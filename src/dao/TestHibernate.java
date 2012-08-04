package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jan 11, 2010
 * Time: 10:00:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestHibernate {

    public User createUser() {
        Session session = HibernateSessionFactory.currentSession();
        Transaction tx = session.beginTransaction();
        User user = new User();
        user.setFirstName("Samir");
        user.setLastName("Mitra");
        user.setImageUrl("/images/samir.jpg");
        user.setAddress("1, Nivedita Lane Kolkata -3");
        user.setCity("Kolkata");
        user.setAdmin(new Integer(1).shortValue());
        user.setEmailId("samir.mitra@gmail.com");
        user.setPassWord("aaaaaa");
        user.setCreateTime(new Date());
        user.setAdmin(new Integer(0).shortValue());

        session.save(user);
        tx.commit();

        HibernateSessionFactory.closeSession();
        return user;
    }

    public Object findbyId(Integer id,Class clazz)
    {
        Session session = HibernateSessionFactory.currentSession();
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
        return retObj;
    }

    public UserToFriend createfriendRequest(User user,User friend)
    {
        Session session = HibernateSessionFactory.currentSession();
        Transaction tx = session.beginTransaction();
       
        UserToFriend userToFriend = new UserToFriend();
        userToFriend.setFriend(friend);
        userToFriend.setUser(user);
        userToFriend.setCreateTime(new Date());
        userToFriend.setHasAccepted(new Integer(0).shortValue());

        session.save(userToFriend);
        tx.commit();
        return  userToFriend;
    }


    public static void main(String[] args) {
        TestHibernate hib = new TestHibernate();
        User user = (User) hib.findbyId(1, User.class);
        User friend = (User) hib.findbyId(2, User.class);
        UserToFriend userToFriend = hib.createfriendRequest(user,friend);
        System.out.println("Success" + userToFriend);
    }
}
