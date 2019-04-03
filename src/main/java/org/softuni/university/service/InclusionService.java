package org.softuni.university.service;

import org.softuni.university.domain.models.service.InclusionServiceModel;

import java.util.List;

public interface InclusionService {

    void createInclusion(String productId, String name) throws Exception;

    List<InclusionServiceModel> findAllInclusions();

    List<InclusionServiceModel> findInclusionsByStudent(String username);
}
