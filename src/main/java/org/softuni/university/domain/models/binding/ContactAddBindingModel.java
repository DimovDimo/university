package org.softuni.university.domain.models.binding;

public class ContactAddBindingModel {

    private String title;
    private String description;
    private String telefon;
    private String postenskiKod;
    private String qdres;
    private String skype;
    private String site;

    public ContactAddBindingModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPostenskiKod() {
        return postenskiKod;
    }

    public void setPostenskiKod(String postenskiKod) {
        this.postenskiKod = postenskiKod;
    }

    public String getQdres() {
        return qdres;
    }

    public void setQdres(String qdres) {
        this.qdres = qdres;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
