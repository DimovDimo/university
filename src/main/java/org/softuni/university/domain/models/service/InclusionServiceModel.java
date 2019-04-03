package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.mappings.IHaveCustomMappings;

import java.math.BigDecimal;

public class InclusionServiceModel implements IHaveCustomMappings {
    private String imageUrl;
    private String name;
    private BigDecimal price;
    private String customer;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Inclusion.class, InclusionServiceModel.class)
                .addMapping(
                        entity -> entity.getCourse().getName(),
                        (dto, value) -> dto.setName((String) value)
                )
                .addMapping(
                        entity -> entity.getCourse().getPrice(),
                        (dto, value) -> dto.setPrice((BigDecimal) value)
                )
                .addMapping(
                        entity -> entity.getCourse().getImageUrl(),
                        (dto, value) -> dto.setImageUrl((String) value)
                )
                .addMapping(
                        entity -> entity.getUser().getUsername(),
                        (dto, value) -> dto.setCustomer((String) value)
                );
    }
}
