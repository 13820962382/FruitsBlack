package mode;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "fruits", schema = "fruits")
@GenericGenerator(name="fruits_id", strategy="increment")
public class FruitsEntity implements Serializable{
    private int fruitsId;
    private String fruitsName;
    private String fruitsImg;
    private CategoryEntity category;
    private String des;

    public FruitsEntity() {
        super();
    }

    public FruitsEntity(String fruitsName, String fruitsImg) {
        this.fruitsName = fruitsName;
        this.fruitsImg = fruitsImg;
}

    @Id
    @Column(name = "fruits_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    @Column(name = "des")
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @ManyToOne(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "CATEGORY_ID")
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return fruitsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FruitsEntity that = (FruitsEntity) o;
        return fruitsId == that.fruitsId &&
                Objects.equals(fruitsName, that.fruitsName) &&
                Objects.equals(fruitsImg, that.fruitsImg) &&
                Objects.equals(des, that.des);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fruitsId, fruitsName, fruitsImg, des);
    }
}
