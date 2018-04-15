package mode;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category", schema = "fruits")

public class CategoryEntity implements Serializable {
    private int categoryId;
    private String categoryName;
    private String des;
    private int totalFruits;
    private Set<FruitsEntity> fruitsEntityList = new HashSet<>();

    public CategoryEntity() {

    }

    public CategoryEntity(String categoryName) {
        this.categoryName = categoryName;
    }

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "des")
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY) //指定一对多的关联关系
    @JoinColumn(name = "category_id",referencedColumnName = "CATEGORY_ID")
    public Set<FruitsEntity> getFruitsEntityList() {
        return fruitsEntityList;
    }

    public void setFruitsEntityList(Set<FruitsEntity> fruitsEntityList) {
        this.fruitsEntityList = fruitsEntityList;
    }

    public int getTotalFruits() {
        totalFruits = fruitsEntityList.size();
        return totalFruits;
    }

    public void setTotalFruits(int totalFruits) {
        this.totalFruits = totalFruits;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return categoryId == that.categoryId &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(des, that.des);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryId, categoryName, des);

    }
}
