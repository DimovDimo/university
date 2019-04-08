package org.softuni.university.domain.models.binding;

public class ContactAddBindingModel {

    private String title;
    private String description;

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
}
