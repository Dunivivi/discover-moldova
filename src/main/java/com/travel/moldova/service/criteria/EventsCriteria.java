package com.travel.moldova.service.criteria;

import com.travel.moldova.domain.User;
import com.travel.moldova.domain.enumeration.Type;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Criteria class for the {@link com.travel.moldova.domain.Events} entity. This class is used
 * in {@link com.travel.moldova.web.rest.EventsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /events?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EventsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private IntegerFilter noOfTours;

    private DoubleFilter rating;

    private StringFilter preViewImg;

    private StringFilter description;

    private TypeFilter type;

    private StringFilter subType;

    private DoubleFilter price;

    private InstantFilter eventDate;

    private StringFilter createdBy;

    private InstantFilter createdDate;

    private StringFilter lastModifiedBy;

    private InstantFilter lastModifiedDate;

    private LongFilter companyId;

    private Boolean distinct;

    public EventsCriteria() {}

    public EventsCriteria(EventsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.noOfTours = other.noOfTours == null ? null : other.noOfTours.copy();
        this.rating = other.rating == null ? null : other.rating.copy();
        this.preViewImg = other.preViewImg == null ? null : other.preViewImg.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.subType = other.subType == null ? null : other.subType.copy();
        this.price = other.price == null ? null : other.price.copy();
        this.eventDate = other.eventDate == null ? null : other.eventDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
        this.lastModifiedBy = other.lastModifiedBy == null ? null : other.lastModifiedBy.copy();
        this.lastModifiedDate = other.lastModifiedDate == null ? null : other.lastModifiedDate.copy();
        this.companyId = other.companyId == null ? null : other.companyId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EventsCriteria copy() {
        return new EventsCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public IntegerFilter getNoOfTours() {
        return noOfTours;
    }

    public IntegerFilter noOfTours() {
        if (noOfTours == null) {
            noOfTours = new IntegerFilter();
        }
        return noOfTours;
    }

    public void setNoOfTours(IntegerFilter noOfTours) {
        this.noOfTours = noOfTours;
    }

    public DoubleFilter getRating() {
        return rating;
    }

    public DoubleFilter rating() {
        if (rating == null) {
            rating = new DoubleFilter();
        }
        return rating;
    }

    public void setRating(DoubleFilter rating) {
        this.rating = rating;
    }

    public StringFilter getPreViewImg() {
        return preViewImg;
    }

    public StringFilter preViewImg() {
        if (preViewImg == null) {
            preViewImg = new StringFilter();
        }
        return preViewImg;
    }

    public void setPreViewImg(StringFilter preViewImg) {
        this.preViewImg = preViewImg;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public TypeFilter getType() {
        return type;
    }

    public void setType(TypeFilter type) {
        this.type = type;
    }

    public StringFilter getSubType() {
        return subType;
    }

    public StringFilter subType() {
        if (subType == null) {
            subType = new StringFilter();
        }
        return subType;
    }

    public void setSubType(StringFilter subType) {
        this.subType = subType;
    }

    public DoubleFilter getPrice() {
        return price;
    }

    public DoubleFilter price() {
        if (price == null) {
            price = new DoubleFilter();
        }
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public InstantFilter getEventDate() {
        return eventDate;
    }

    public InstantFilter eventDate() {
        if (eventDate == null) {
            eventDate = new InstantFilter();
        }
        return eventDate;
    }

    public void setEventDate(InstantFilter eventDate) {
        this.eventDate = eventDate;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getCreatedDate() {
        return createdDate;
    }

    public InstantFilter createdDate() {
        if (createdDate == null) {
            createdDate = new InstantFilter();
        }
        return createdDate;
    }

    public void setCreatedDate(InstantFilter createdDate) {
        this.createdDate = createdDate;
    }

    public StringFilter getLastModifiedBy() {
        return lastModifiedBy;
    }

    public StringFilter lastModifiedBy() {
        if (lastModifiedBy == null) {
            lastModifiedBy = new StringFilter();
        }
        return lastModifiedBy;
    }

    public void setLastModifiedBy(StringFilter lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public InstantFilter getLastModifiedDate() {
        return lastModifiedDate;
    }

    public InstantFilter lastModifiedDate() {
        if (lastModifiedDate == null) {
            lastModifiedDate = new InstantFilter();
        }
        return lastModifiedDate;
    }

    public void setLastModifiedDate(InstantFilter lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public LongFilter companyId() {
        if (companyId == null) {
            companyId = new LongFilter();
        }
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EventsCriteria that = (EventsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(noOfTours, that.noOfTours) &&
            Objects.equals(rating, that.rating) &&
            Objects.equals(preViewImg, that.preViewImg) &&
            Objects.equals(description, that.description) &&
            Objects.equals(type, that.type) &&
            Objects.equals(subType, that.subType) &&
            Objects.equals(price, that.price) &&
            Objects.equals(eventDate, that.eventDate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdDate, that.createdDate) &&
            Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
            Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
            Objects.equals(companyId, that.companyId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            title,
            noOfTours,
            rating,
            preViewImg,
            description,
            type,
            subType,
            price,
            eventDate,
            createdBy,
            createdDate,
            lastModifiedBy,
            lastModifiedDate,
            companyId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (noOfTours != null ? "noOfTours=" + noOfTours + ", " : "") +
            (rating != null ? "rating=" + rating + ", " : "") +
            (preViewImg != null ? "preViewImg=" + preViewImg + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (subType != null ? "subType=" + subType + ", " : "") +
            (price != null ? "price=" + price + ", " : "") +
            (eventDate != null ? "eventDate=" + eventDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (lastModifiedBy != null ? "lastModifiedBy=" + lastModifiedBy + ", " : "") +
            (lastModifiedDate != null ? "lastModifiedDate=" + lastModifiedDate + ", " : "") +
            (companyId != null ? "companyId=" + companyId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }

    public static class TypeFilter extends Filter<Type> {

        public TypeFilter() {
        }

        public TypeFilter(TypeFilter type) {
            super(type);
        }

        @Override
        public TypeFilter copy() {
            return new TypeFilter(this);
        }
    }
}
