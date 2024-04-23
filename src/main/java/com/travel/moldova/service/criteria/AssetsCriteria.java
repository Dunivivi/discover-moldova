package com.travel.moldova.service.criteria;

import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link com.travel.moldova.domain.Assets} entity. This class is used
 * in {@link com.travel.moldova.web.rest.AssetsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /assets?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AssetsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter url;

    private LongFilter eventsId;

    private Boolean distinct;

    public AssetsCriteria() {}

    public AssetsCriteria(AssetsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.url = other.url == null ? null : other.url.copy();
        this.eventsId = other.eventsId == null ? null : other.eventsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AssetsCriteria copy() {
        return new AssetsCriteria(this);
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

    public StringFilter getUrl() {
        return url;
    }

    public StringFilter url() {
        if (url == null) {
            url = new StringFilter();
        }
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public LongFilter getEventsId() {
        return eventsId;
    }

    public LongFilter eventsId() {
        if (eventsId == null) {
            eventsId = new LongFilter();
        }
        return eventsId;
    }

    public void setEventsId(LongFilter eventsId) {
        this.eventsId = eventsId;
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
        final AssetsCriteria that = (AssetsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(url, that.url) &&
            Objects.equals(eventsId, that.eventsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, eventsId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AssetsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (url != null ? "url=" + url + ", " : "") +
            (eventsId != null ? "eventsId=" + eventsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
