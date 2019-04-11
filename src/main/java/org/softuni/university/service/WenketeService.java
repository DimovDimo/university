package org.softuni.university.service;

import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.service.WenketeServiceModel;

import java.util.List;

public interface WenketeService {

    void createWenkete(WenketeServiceModel wenketeServiceModel, String name) throws Exception;

    List<WenketeServiceModel> findAllWenketes();
}
