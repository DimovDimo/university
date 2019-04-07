package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {
    private String title;
    private String description;
    User user;

    public Contact() {
    }

    @Column(name = "contact_title",nullable = false, unique = false, updatable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "contact_description",nullable = false, unique = false, updatable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
