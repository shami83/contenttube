package dao;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Jan 18, 2010
 * Time: 9:43:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserToFriend {
    private Integer id;
    private User user;
    private User friend;
    private short hasAccepted;
    private Date createTime;
    private Date modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public short getHasAccepted() {
        return hasAccepted;
    }

    public void setHasAccepted(short hasAccepted) {
        this.hasAccepted = hasAccepted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
