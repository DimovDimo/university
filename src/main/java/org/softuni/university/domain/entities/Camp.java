package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "camps")
public class Camp extends BaseEntity {
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

    User user;

    public Camp() {
    }

    @Column(name = "nostuwki",nullable = false, unique = false, updatable = false)
    public int getNostuwki() {
        return nostuwki;
    }

    public void setNostuwki(int nostuwki) {
        this.nostuwki = nostuwki;
    }

    @Column(name = "leglq",nullable = false, unique = false, updatable = false)
    public int getLeglq() {
        return leglq;
    }

    public void setLeglq(int leglq) {
        this.leglq = leglq;
    }

    @Column(name = "pqrkoMestq",nullable = false, unique = false, updatable = false)
    public int getPqrkoMestq() {
        return pqrkoMestq;
    }

    public void setPqrkoMestq(int pqrkoMestq) {
        this.pqrkoMestq = pqrkoMestq;
    }

    @Column(name = "zelqniNqpitki",nullable = true, unique = false, updatable = false)
    public String getZelqniNqpitki() {
        return zelqniNqpitki;
    }

    public void setZelqniNqpitki(String zelqniNqpitki) {
        this.zelqniNqpitki = zelqniNqpitki;
    }

    @Column(name = "zelqniSpeciqlniqstiq",nullable = true, unique = false, updatable = false)
    public String getZelqniSpeciqlniqstiq() {
        return zelqniSpeciqlniqstiq;
    }

    public void setZelqniSpeciqlniqstiq(String zelqniSpeciqlniqstiq) {
        this.zelqniSpeciqlniqstiq = zelqniSpeciqlniqstiq;
    }

    @Column(name = "zelqnqMuzikq",nullable = true, unique = false, updatable = false)
    public String getZelqnqMuzikq() {
        return zelqnqMuzikq;
    }

    public void setZelqnqMuzikq(String zelqnqMuzikq) {
        this.zelqnqMuzikq = zelqnqMuzikq;
    }

    @Column(name = "zelqnTuristiceskoObektWokolnosttq",nullable = true, unique = false, updatable = false)
    public String getZelqnTuristiceskoObektWokolnosttq() {
        return zelqnTuristiceskoObektWokolnosttq;
    }

    public void setZelqnTuristiceskoObektWokolnosttq(String zelqnTuristiceskoObektWokolnosttq) {
        this.zelqnTuristiceskoObektWokolnosttq = zelqnTuristiceskoObektWokolnosttq;
    }

    @Column(name = "predlovenieZqOtbornqIgrq",nullable = true, unique = false, updatable = false)
    public String getPredlovenieZqOtbornqIgrq() {
        return predlovenieZqOtbornqIgrq;
    }

    public void setPredlovenieZqOtbornqIgrq(String predlovenieZqOtbornqIgrq) {
        this.predlovenieZqOtbornqIgrq = predlovenieZqOtbornqIgrq;
    }

    @Column(name = "ocqwqniWpecqtleniq",nullable = true, unique = false, updatable = false)
    public String getOcqwqniWpecqtleniq() {
        return ocqwqniWpecqtleniq;
    }

    public void setOcqwqniWpecqtleniq(String ocqwqniWpecqtleniq) {
        this.ocqwqniWpecqtleniq = ocqwqniWpecqtleniq;
    }

    @Column(name = "kqkwoNeIskqteDqImq",nullable = true, unique = false, updatable = false)
    public String getKqkwoNeIskqteDqImq() {
        return kqkwoNeIskqteDqImq;
    }

    public void setKqkwoNeIskqteDqImq(String kqkwoNeIskqteDqImq) {
        this.kqkwoNeIskqteDqImq = kqkwoNeIskqteDqImq;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
