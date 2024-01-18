package com.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ciutada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ciutadaId;

    private Long ciutatId;
    private String nom;
    private String cognom;
    private int edat;

    // Constructores
    public Ciutada() {
    }

    public Ciutada(Long ciutatId, String nom, String cognom, int edat) {
        this.ciutatId = ciutatId;
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
    }

    // Getters y setters

    public Long getCiutadaId() {
        return ciutadaId;
    }

    public void setCiutadaId(Long ciutadaId) {
        this.ciutadaId = ciutadaId;
    }

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

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    // Método toString
    @Override
    public String toString() {
        return "Ciutada " +

                ", ciutatId=" + ciutatId +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", edat=" + edat;
    }
}
