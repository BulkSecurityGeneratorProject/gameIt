package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 1/16/2016.
 */
@Entity
@Table
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NotNull
    @Column(nullable = false, unique = true)
    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<HardwareProduct> hardwareProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!getCategoryId().equals(category.getCategoryId())) return false;
        return getCategoryName().equals(category.getCategoryName());

    }

    @Override
    public int hashCode() {
        int result = getCategoryId().hashCode();
        result = 31 * result + getCategoryName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Category() {
        hardwareProducts = new ArrayList<HardwareProduct>();
    }

    public List<HardwareProduct> getHardwareProducts() {
        return hardwareProducts;
    }

    public void setHardwareProducts(List<HardwareProduct> hardwareProducts) {
        this.hardwareProducts = hardwareProducts;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
