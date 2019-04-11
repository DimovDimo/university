package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Camp;
import org.softuni.university.domain.entities.Wenkete;
import org.softuni.university.mappings.IHaveCustomMappings;

public class CampServiceModel implements IHaveCustomMappings {
    private int nostuwki;
    private int leglq;
    private int pqrkoMestq;
    private String zelqniNqpitki;
    private String zelqniSpeciqlniqstiq;
    private String zelqnqMuzikq;
    private String zelqnTuristiceskoObektWokolnosttq;
    private String predlovenieZqOtbornqIgrq;
    private String ocqwqniWpecqtleniq;
    private String kqkwoNeIskqteDqImq;
    private String student;

    public int getNostuwki() {
        return nostuwki;
    }

    public void setNostuwki(int nostuwki) {
        this.nostuwki = nostuwki;
    }

    public int getLeglq() {
        return leglq;
    }

    public void setLeglq(int leglq) {
        this.leglq = leglq;
    }

    public int getPqrkoMestq() {
        return pqrkoMestq;
    }

    public void setPqrkoMestq(int pqrkoMestq) {
        this.pqrkoMestq = pqrkoMestq;
    }

    public String getZelqniNqpitki() {
        return zelqniNqpitki;
    }

    public void setZelqniNqpitki(String zelqniNqpitki) {
        this.zelqniNqpitki = zelqniNqpitki;
    }

    public String getZelqniSpeciqlniqstiq() {
        return zelqniSpeciqlniqstiq;
    }

    public void setZelqniSpeciqlniqstiq(String zelqniSpeciqlniqstiq) {
        this.zelqniSpeciqlniqstiq = zelqniSpeciqlniqstiq;
    }

    public String getZelqnqMuzikq() {
        return zelqnqMuzikq;
    }

    public void setZelqnqMuzikq(String zelqnqMuzikq) {
        this.zelqnqMuzikq = zelqnqMuzikq;
    }

    public String getZelqnTuristiceskoObektWokolnosttq() {
        return zelqnTuristiceskoObektWokolnosttq;
    }

    public void setZelqnTuristiceskoObektWokolnosttq(String zelqnTuristiceskoObektWokolnosttq) {
        this.zelqnTuristiceskoObektWokolnosttq = zelqnTuristiceskoObektWokolnosttq;
    }

    public String getPredlovenieZqOtbornqIgrq() {
        return predlovenieZqOtbornqIgrq;
    }

    public void setPredlovenieZqOtbornqIgrq(String predlovenieZqOtbornqIgrq) {
        this.predlovenieZqOtbornqIgrq = predlovenieZqOtbornqIgrq;
    }

    public String getOcqwqniWpecqtleniq() {
        return ocqwqniWpecqtleniq;
    }

    public void setOcqwqniWpecqtleniq(String ocqwqniWpecqtleniq) {
        this.ocqwqniWpecqtleniq = ocqwqniWpecqtleniq;
    }

    public String getKqkwoNeIskqteDqImq() {
        return kqkwoNeIskqteDqImq;
    }

    public void setKqkwoNeIskqteDqImq(String kqkwoNeIskqteDqImq) {
        this.kqkwoNeIskqteDqImq = kqkwoNeIskqteDqImq;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Camp.class, CampServiceModel.class)
                .addMapping(
                        camp -> camp.getUser().getUsername(),
                        (dto, value) -> dto.setStudent((String) value)
                );
    }
}
