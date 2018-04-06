package mode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fruits", schema = "fruits", catalog = "")
public class FruitsEntity {
    private int fruitsId;
    private String fruitsName;
    private String fruitsImg;
    private int categoryId;
    private Integer totalFruit;
    private String des;

    @Id
    @Column(name = "fruits_id")
    public int getFruitsId() {
        return fruitsId;
    }

    public void setFruitsId(int fruitsId) {
        this.fruitsId = fruitsId;
    }

    @Basic
    @Column(name = "fruits_name")
    public String getFruitsName() {
        return fruitsName;
    }

    public void setFruitsName(String fruitsName) {
        this.fruitsName = fruitsName;
    }

    @Basic
    @Column(name = "fruits_img")
    public String getFruitsImg() {
        return fruitsImg;
    }

    public void setFruitsImg(String fruitsImg) {
        this.fruitsImg = fruitsImg;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FruitsEntity that = (FruitsEntity) o;
        return fruitsId == that.fruitsId &&
                categoryId == that.categoryId &&
                Objects.equals(fruitsName, that.fruitsName) &&
                Objects.equals(fruitsImg, that.fruitsImg) &&
                Objects.equals(totalFruit, that.totalFruit) &&
                Objects.equals(des, that.des);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fruitsId, fruitsName, fruitsImg, categoryId, totalFruit, des);
    }
}
