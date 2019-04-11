package org.softuni.university.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wenketes")
public class Wenkete extends BaseEntity {
    private String hqreswqne;
    private String neHqreswqne;
    private String nqjLesno;
    private String nqjTrudno;
    private String kqkwoIskqteDqPromenite;
    private String nqKoKqzqhte;
    private String rqbotqtqSKursqLiESwyrzqnq;
    private String kqkwoBihqTyrsiliRqbotodqteliteWKursq;

    User user;

    public Wenkete() {
    }

    @Column(name = "hqreswqne",nullable = true, unique = false, updatable = false)
    public String getHqreswqne() {
        return hqreswqne;
    }

    public void setHqreswqne(String hqreswqne) {
        this.hqreswqne = hqreswqne;
    }

    @Column(name = "neHqreswqne",nullable = true, unique = false, updatable = false)
    public String getNeHqreswqne() {
        return neHqreswqne;
    }

    public void setNeHqreswqne(String neHqreswqne) {
        this.neHqreswqne = neHqreswqne;
    }

    @Column(name = "nqjLesno",nullable = true, unique = false, updatable = false)
    public String getNqjLesno() {
        return nqjLesno;
    }

    public void setNqjLesno(String nqjLesno) {
        this.nqjLesno = nqjLesno;
    }

    @Column(name = "nqjTrudno",nullable = true, unique = false, updatable = false)
    public String getNqjTrudno() {
        return nqjTrudno;
    }

    public void setNqjTrudno(String nqjTrudno) {
        this.nqjTrudno = nqjTrudno;
    }

    @Column(name = "kqkwoIskqteDqPromenite",nullable = true, unique = false, updatable = false)
    public String getKqkwoIskqteDqPromenite() {
        return kqkwoIskqteDqPromenite;
    }

    public void setKqkwoIskqteDqPromenite(String kqkwoIskqteDqPromenite) {
        this.kqkwoIskqteDqPromenite = kqkwoIskqteDqPromenite;
    }

    @Column(name = "nqKoKqzqhte",nullable = true, unique = false, updatable = false)
    public String getNqKoKqzqhte() {
        return nqKoKqzqhte;
    }

    public void setNqKoKqzqhte(String nqKoKqzqhte) {
        this.nqKoKqzqhte = nqKoKqzqhte;
    }

    @Column(name = "rqbotqtqSKursqLiESwyrzqnq",nullable = true, unique = false, updatable = false)
    public String getRqbotqtqSKursqLiESwyrzqnq() {
        return rqbotqtqSKursqLiESwyrzqnq;
    }

    public void setRqbotqtqSKursqLiESwyrzqnq(String rqbotqtqSKursqLiESwyrzqnq) {
        this.rqbotqtqSKursqLiESwyrzqnq = rqbotqtqSKursqLiESwyrzqnq;
    }

    @Column(name = "kqkwoBihqTyrsiliRqbotodqteliteWKursq",nullable = true, unique = false, updatable = false)
    public String getKqkwoBihqTyrsiliRqbotodqteliteWKursq() {
        return kqkwoBihqTyrsiliRqbotodqteliteWKursq;
    }

    public void setKqkwoBihqTyrsiliRqbotodqteliteWKursq(String kqkwoBihqTyrsiliRqbotodqteliteWKursq) {
        this.kqkwoBihqTyrsiliRqbotodqteliteWKursq = kqkwoBihqTyrsiliRqbotodqteliteWKursq;
    }

    @ManyToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
