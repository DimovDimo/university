package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Contact;
import org.softuni.university.mappings.IHaveCustomMappings;

public class ContactServiceModel implements IHaveCustomMappings {
    private String title;
    private String description;
    private String telefon;
    private String postenskiKod;
    private String qdres;
    private String skype;
    private String site;
    private String student;

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

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Contact.class, ContactServiceModel.class)
                .addMapping(
                        contact -> contact.getUser().getUsername(),
                        (dto, value) -> dto.setStudent((String) value)
                );
    }
}
