package mk.gameIt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 1/15/2016.
 */
@Entity
@Table
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @NotNull
    @Column(unique = true, nullable=false)
    private String companyName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PublishedGame", joinColumns = {@JoinColumn(name = "companyId")}, inverseJoinColumns = {@JoinColumn(name = "gameId")})
    private List<Game> publishedGames;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PublishedHardware", joinColumns = {@JoinColumn(name = "companyId")}, inverseJoinColumns = {@JoinColumn(name = "hardwareId")})
    private List<HardwareProduct> publishedHardware;

    public Company() {
        publishedGames = new ArrayList<Game>();
        publishedHardware = new ArrayList<HardwareProduct>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;

        Company company = (Company) o;

        if (!getCompanyId().equals(company.getCompanyId())) return false;
        return getCompanyName().equals(company.getCompanyName());

    }

    @Override
    public int hashCode() {
        int result = getCompanyId().hashCode();
        result = 31 * result + getCompanyName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public List<HardwareProduct> getPublishedHardware() {
        return publishedHardware;
    }

    public void setPublishedHardware(List<HardwareProduct> publishedHardware) {
        this.publishedHardware = publishedHardware;
    }

    public List<Game> getPublishedGames() {
        return publishedGames;
    }

    public void setPublishedGames(List<Game> publishedGames) {
        this.publishedGames = publishedGames;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
