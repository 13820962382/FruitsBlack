package mode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "category", schema = "fruits")
public class CategoryEntity {
    private int categoryId;
    private String categoryName;
    private Integer totalFruit;
    private String des;
    private Set<FruitsEntity> fruitsEntityList;

    @Id
    @Column(name = "category_id")
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
    @Column(name = "total_fruit")
    public Integer getTotalFruit() {
        return totalFruit;
    }

    public void setTotalFruit(Integer totalFruit) {
        this.totalFruit = totalFruit;
    }

    @Basic
    @Column(name = "des")
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @OneToMany(mappedBy="")
    public Set<FruitsEntity> getFruitsEntityList() {
        return fruitsEntityList;
    }

    public void setFruitsEntityList(Set<FruitsEntity> fruitsEntityList) {
        this.fruitsEntityList = fruitsEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return categoryId == that.categoryId &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(totalFruit, that.totalFruit) &&
                Objects.equals(des, that.des);
    }

    @Override
    public int hashCode() {

        return Objects.hash(categoryId, categoryName, totalFruit, des);

    }
}
