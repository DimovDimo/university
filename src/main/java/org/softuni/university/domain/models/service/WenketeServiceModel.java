package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Wenkete;
import org.softuni.university.mappings.IHaveCustomMappings;

public class WenketeServiceModel implements IHaveCustomMappings {

    private String hqreswqne;
    private String neHqreswqne;
    private String nqjLesno;
    private String nqjTrudno;
    private String kqkwoIskqteDqPromenite;
    private String nqKoKqzqhte;
    private String rqbotqtqSKursqLiESwyrzqnq;
    private String kqkwoBihqTyrsiliRqbotodqteliteWKursq;
    private String student;

    public String getHqreswqne() {
        return hqreswqne;
    }

    public void setHqreswqne(String hqreswqne) {
        this.hqreswqne = hqreswqne;
    }

    public String getNeHqreswqne() {
        return neHqreswqne;
    }

    public void setNeHqreswqne(String neHqreswqne) {
        this.neHqreswqne = neHqreswqne;
    }

    public String getNqjLesno() {
        return nqjLesno;
    }

    public void setNqjLesno(String nqjLesno) {
        this.nqjLesno = nqjLesno;
    }

    public String getNqjTrudno() {
        return nqjTrudno;
    }

    public void setNqjTrudno(String nqjTrudno) {
        this.nqjTrudno = nqjTrudno;
    }

    public String getKqkwoIskqteDqPromenite() {
        return kqkwoIskqteDqPromenite;
    }

    public void setKqkwoIskqteDqPromenite(String kqkwoIskqteDqPromenite) {
        this.kqkwoIskqteDqPromenite = kqkwoIskqteDqPromenite;
    }

    public String getNqKoKqzqhte() {
        return nqKoKqzqhte;
    }

    public void setNqKoKqzqhte(String nqKoKqzqhte) {
        this.nqKoKqzqhte = nqKoKqzqhte;
    }

    public String getRqbotqtqSKursqLiESwyrzqnq() {
        return rqbotqtqSKursqLiESwyrzqnq;
    }

    public void setRqbotqtqSKursqLiESwyrzqnq(String rqbotqtqSKursqLiESwyrzqnq) {
        this.rqbotqtqSKursqLiESwyrzqnq = rqbotqtqSKursqLiESwyrzqnq;
    }

    public String getKqkwoBihqTyrsiliRqbotodqteliteWKursq() {
        return kqkwoBihqTyrsiliRqbotodqteliteWKursq;
    }

    public void setKqkwoBihqTyrsiliRqbotodqteliteWKursq(String kqkwoBihqTyrsiliRqbotodqteliteWKursq) {
        this.kqkwoBihqTyrsiliRqbotodqteliteWKursq = kqkwoBihqTyrsiliRqbotodqteliteWKursq;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Wenkete.class, WenketeServiceModel.class)
                .addMapping(
                        wenkete -> wenkete.getUser().getUsername(),
                        (dto, value) -> dto.setStudent((String) value)
                );
    }
}
