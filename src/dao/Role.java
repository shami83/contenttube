package dao;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 8, 2010
 * Time: 9:38:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Role {
    private Integer id;
    private Integer index;
    private Integer cumulativeBitmap;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getCumulativeBitmap() {
        return cumulativeBitmap;
    }

    public void setCumulativeBitmap(Integer cumulativeBitmap) {
        this.cumulativeBitmap = cumulativeBitmap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (index != null ? index.hashCode() : 0);
        result = 31 * result + (cumulativeBitmap != null ? cumulativeBitmap.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
