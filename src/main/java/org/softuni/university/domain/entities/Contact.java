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
    private String telefon;
    private String postenskiKod;
    private String qdres;
    private String skype;
    private String site;

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

    @Column(name = "telefon",nullable = true, unique = false, updatable = false)
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Column(name = "postenskiKod",nullable = true, unique = false, updatable = false)
    public String getPostenskiKod() {
        return postenskiKod;
    }

    public void setPostenskiKod(String postenskiKod) {
        this.postenskiKod = postenskiKod;
    }

    @Column(name = "qdres",nullable = true, unique = false, updatable = false)
    public String getQdres() {
        return qdres;
    }

    public void setQdres(String qdres) {
        this.qdres = qdres;
    }

    @Column(name = "skype",nullable = true, unique = false, updatable = false)
    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Column(name = "site",nullable = true, unique = false, updatable = false)
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
