package com.travel.moldova.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.travel.moldova.domain.enumeration.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Events.
 */
@Entity
@Table(name = "events")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "no_of_tours")
    private Integer noOfTours;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "pre_view_img")
    private String preViewImg;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @Size(max = 50)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50, nullable = true) // Add nullable attribute
    private Type type;

    @Size(max = 50)
    @Column(name = "sub_type", length = 50)
    private String subType;

    @Column(name = "price")
    private Double price;

    @Column(name = "event_date")
    private Instant eventDate;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "company_id")
    private Long companyId;

    @OneToMany(mappedBy = "events")
    @JsonIgnoreProperties(value = {"events"})
    private Set<Assets> assets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Events id(Long id) {
        this.setId(id);
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Events title(String title) {
        this.setTitle(title);
        return this;
    }

    public Integer getNoOfTours() {
        return this.noOfTours;
    }

    public void setNoOfTours(Integer noOfTours) {
        this.noOfTours = noOfTours;
    }

    public Events noOfTours(Integer noOfTours) {
        this.setNoOfTours(noOfTours);
        return this;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Events rating(Double rating) {
        this.setRating(rating);
        return this;
    }

    public String getPreViewImg() {
        return this.preViewImg;
    }

    public void setPreViewImg(String preViewImg) {
        this.preViewImg = preViewImg;
    }

    public Events preViewImg(String preViewImg) {
        this.setPreViewImg(preViewImg);
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Events description(String description) {
        this.setDescription(description);
        return this;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Events type(Type type) {
        this.setType(type);
        return this;
    }

    public String getSubType() {
        return this.subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Events subType(String subType) {
        this.setSubType(subType);
        return this;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Events price(Double price) {
        this.setPrice(price);
        return this;
    }

    public Instant getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(Instant eventDate) {
        this.eventDate = eventDate;
    }

    public Events eventDate(Instant eventDate) {
        this.setEventDate(eventDate);
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Events createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Events createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Events lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public Instant getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Events lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Events companyId(Long companyId) {
        this.setCompanyId(companyId);
        return this;
    }

    public Set<Assets> getAssets() {
        return assets;
    }

    public void setAssets(Set<Assets> assets) {
        this.assets = assets;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Events)) {
            return false;
        }
        return id != null && id.equals(((Events) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Events{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", noOfTours=" + getNoOfTours() +
            ", rating=" + getRating() +
            ", preViewImg='" + getPreViewImg() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", subType='" + getSubType() + "'" +
            ", price=" + getPrice() +
            ", eventDate='" + getEventDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", companyId=" + getCompanyId() +
            "}";
    }
}
