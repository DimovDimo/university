package org.softuni.university.domain.models.view;

import java.math.BigDecimal;

public class ContactViewModel {
    private String title;
    private String description;
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

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
