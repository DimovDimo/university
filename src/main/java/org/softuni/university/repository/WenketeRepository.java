package org.softuni.university.repository;

import org.softuni.university.domain.entities.Wenkete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WenketeRepository extends JpaRepository<Wenkete, String> {
    List<Wenkete> findAllByUser_Username(String username);
}
