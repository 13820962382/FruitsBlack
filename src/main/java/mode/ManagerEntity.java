package mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "manager", schema = "fruits", catalog = "")
public class ManagerEntity {
    private int managerId;
    private String managerName;
    private String managerPassword;

    @Id
    @Column(name = "manager_id")
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "manager_name")
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Basic
    @Column(name = "manager_password")
    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManagerEntity that = (ManagerEntity) o;
        return managerId == that.managerId &&
                Objects.equals(managerName, that.managerName) &&
                Objects.equals(managerPassword, that.managerPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(managerId, managerName, managerPassword);
    }
}
