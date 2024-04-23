package com.travel.moldova.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Assets.
 */
@Entity
@Table(name = "assets")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Assets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JsonIgnore
    private Events events;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Assets id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return this.url;
    }

    public Assets url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Events getEvents() {
        return this.events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public Assets events(Events events) {
        this.setEvents(events);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assets)) {
            return false;
        }
        return id != null && id.equals(((Assets) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Assets{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
