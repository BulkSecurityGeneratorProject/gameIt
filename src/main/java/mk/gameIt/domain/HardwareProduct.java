package mk.gameIt.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Stefan on 1/15/2016.
 */
@Entity
@Table
public class HardwareProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hardwareId;
    @Column(nullable = false)
    private String hardwarePerformance;
    @Column(nullable = false)
    private String hardwareModelName;

    @Column(nullable = false)
    private Date hardwareProductionYear;
    @Column(nullable = false)
    private String hardwarePicturePath;
    @Column(nullable = false)
    private String hardwareDescription;
    @Column(nullable = false)
    private Long hardwareNumberOfViews;

    private Double hardwareGradeSum;

    @ManyToOne
    @JoinTable(name = "TypeOfHardware", joinColumns = {@JoinColumn(name = "hardwareId")}, inverseJoinColumns = {@JoinColumn(name = "categoryId")})
    private Category category;
/*TODO:FINISH HERE
    @ManyToMany(mappedBy = "izdadenHardver")
    private List<Company> kompanii;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.hardver")
    private List<CommentHardwareProduct> comments;*/

    @OneToMany(mappedBy = "hardwareId")
    private List<CommentHardwareProduct> comments;

    @OneToMany(mappedBy = "hardwareId")
    private List<HardwareProductRating> oceni;

    public HardwareProduct() {
     //   kompanii = new ArrayList<Company>();
    //    comments = new ArrayList<CommentHardwareProduct>();
        oceni = new ArrayList<HardwareProductRating>();
    }

    public List<HardwareProductRating> getOceni() {
        return oceni;
    }

/*    public List<CommentHardwareProduct> getComments() {
        return comments;
    }

    public void setComments(List<CommentHardwareProduct> comments) {
        this.comments = comments;
    }

    public List<Company> getGameCompanies() {
        return kompanii;
    }

    public void setGameCompanies(List<Company> kompanii) {
        this.kompanii = kompanii;
    }*/

    public Date getHardwareProductionYear() {
        return hardwareProductionYear;
    }

    public void setHardwareProductionYear(Date hardwareProductionYear) {
        this.hardwareProductionYear = hardwareProductionYear;
    }

    public Double getHardwareGradeSum() {
        return hardwareGradeSum;
    }

    public void setHardwareGradeSum(Double hardwareGradeSum) {
        this.hardwareGradeSum = hardwareGradeSum;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getHardwareNumberOfViews() {
        return hardwareNumberOfViews;
    }

    public void setHardwareNumberOfViews(long hardwareNumberOfViews) {
        this.hardwareNumberOfViews = hardwareNumberOfViews;
    }

    public Long getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(long hardwareId) {
        this.hardwareId = hardwareId;
    }

    public String getHardwareDescription() {
        return hardwareDescription;
    }

    public void setHardwareDescription(String hardwareDescription) {
        this.hardwareDescription = hardwareDescription;
    }

    public String getHardwareModelName() {
        return hardwareModelName;
    }

    public void setHardwareModelName(String hardwareModelName) {
        this.hardwareModelName = hardwareModelName;
    }

    public String getHardwarePerformance() {
        return hardwarePerformance;
    }

    public void setHardwarePerformance(String hardwarePerformance) {
        this.hardwarePerformance = hardwarePerformance;
    }

    public String getHardwarePicturePath() {
        return hardwarePicturePath;
    }

    public void setHardwarePicturePath(String hardwarePicturePath) {
        this.hardwarePicturePath = hardwarePicturePath;
    }

    public void setHardid(Long hardid) {
        this.hardwareId = hardid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HardwareProduct)) return false;

        HardwareProduct that = (HardwareProduct) o;

        if (getHardwareNumberOfViews() != that.getHardwareNumberOfViews()) return false;
        if (!getHardwareId().equals(that.getHardwareId())) return false;
        if (!getHardwarePerformance().equals(that.getHardwarePerformance())) return false;
        if (!getHardwareModelName().equals(that.getHardwareModelName())) return false;
        if (!getHardwareProductionYear().equals(that.getHardwareProductionYear())) return false;
        if (!getHardwarePicturePath().equals(that.getHardwarePicturePath())) return false;
        if (!getHardwareDescription().equals(that.getHardwareDescription())) return false;
        if (getHardwareGradeSum() != null ? !getHardwareGradeSum().equals(that.getHardwareGradeSum()) : that.getHardwareGradeSum() != null)
            return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;

        return getOceni() != null ? getOceni().equals(that.getOceni()) : that.getOceni() == null;

    }

    @Override
    public int hashCode() {
        int result = getHardwareId().hashCode();
        result = 31 * result + getHardwarePerformance().hashCode();
        result = 31 * result + getHardwareModelName().hashCode();
        result = 31 * result + getHardwareProductionYear().hashCode();
        result = 31 * result + getHardwarePicturePath().hashCode();
        result = 31 * result + getHardwareDescription().hashCode();
        result = 31 * result + (int) (getHardwareNumberOfViews() ^ (getHardwareNumberOfViews() >>> 32));
        result = 31 * result + (getHardwareGradeSum() != null ? getHardwareGradeSum().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getOceni() != null ? getOceni().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HardwareProduct{" +
                "hardwareId=" + hardwareId +
                ", hardwarePerformance='" + hardwarePerformance + '\'' +
                ", hardwareModelName='" + hardwareModelName + '\'' +
                ", hardwareProductionYear=" + hardwareProductionYear +
                ", hardwarePicturePath='" + hardwarePicturePath + '\'' +
                ", hardwareDescription='" + hardwareDescription + '\'' +
                ", hardwareNumberOfViews=" + hardwareNumberOfViews +
                ", hardwareGradeSum=" + hardwareGradeSum +
                ", category=" + category +
                '}';
    }
}
