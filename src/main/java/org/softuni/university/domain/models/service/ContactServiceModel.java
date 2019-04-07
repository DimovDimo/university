package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Contact;
import org.softuni.university.mappings.IHaveCustomMappings;

import java.math.BigDecimal;

public class ContactServiceModel implements IHaveCustomMappings {
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

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Contact.class, ContactServiceModel.class)
                .addMapping(
                        contact -> contact.getUser().getUsername(),
                        (dto, value) -> dto.setStudent((String) value)
                );
    }
}
