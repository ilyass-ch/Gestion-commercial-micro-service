package net.chakir.marketingservice.entities;

import jakarta.persistence.*;
import net.chakir.marketingservice.enums.CompagneStatut;

import java.util.Date;

@Entity
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private CompagneStatut compagneStatut;

    public Campagne() {
    }

    public Campagne(Long id, String nom, String description, Date dateDebut, Date dateFin, CompagneStatut compagneStatut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.compagneStatut = compagneStatut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public CompagneStatut getStatut() {
        return compagneStatut;
    }

    public void setStatut(CompagneStatut compagneStatut) {
        this.compagneStatut = compagneStatut;
    }
}
