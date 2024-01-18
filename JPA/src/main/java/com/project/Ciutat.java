package com.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ciutat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ciutatId;

    private String nom;
    private String pais;
    private int codiPostal;

    // Constructores
    public Ciutat() {
    }

    public Ciutat(String nom, String pais, int codiPostal) {
        this.nom = nom;
        this.pais = pais;
        this.codiPostal = codiPostal;
    }

    // Getters y setters

    public Long getCiutatId() {
        return ciutatId;
    }

    public void setCiutatId(Long ciutatId) {
        this.ciutatId = ciutatId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(int codiPostal) {
        this.codiPostal = codiPostal;
    }

    // Método toString
    @Override
    public String toString() {
        return "Ciutat" +
                ", nom='" + nom + '\'' +
                ", pais='" + pais + '\'' +
                ", codiPostal=" + codiPostal;
    }
}
