package dao;

/**
 * Created by IntelliJ IDEA.
 * User: Shamik Mitra
 * Date: Mar 8, 2010
 * Time: 9:33:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Operation {

    private Integer id;
    private String operationName;
    private String displayName;
    private  Integer bitmap;

    public Integer getBitmap() {
        return bitmap;
    }

    public void setBitmap(Integer bitmap) {
        this.bitmap = bitmap;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != null ? !id.equals(operation.id) : operation.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
