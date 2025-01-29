package net.chakir.marketingservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StatistiqueCampagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long campagneId;
    private int nombreEmailsEnvoyes;
    private double tauxOuverture;
    private double tauxConversion;

    // Constructeurs
    public StatistiqueCampagne() {}

    public StatistiqueCampagne(Long campagneId, int nombreEmailsEnvoyes, double tauxOuverture, double tauxConversion) {
        this.campagneId = campagneId;
        this.nombreEmailsEnvoyes = nombreEmailsEnvoyes;
        this.tauxOuverture = tauxOuverture;
        this.tauxConversion = tauxConversion;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampagneId() {
        return campagneId;
    }

    public void setCampagneId(Long campagneId) {
        this.campagneId = campagneId;
    }

    public int getNombreEmailsEnvoyes() {
        return nombreEmailsEnvoyes;
    }

    public void setNombreEmailsEnvoyes(int nombreEmailsEnvoyes) {
        this.nombreEmailsEnvoyes = nombreEmailsEnvoyes;
    }

    public double getTauxOuverture() {
        return tauxOuverture;
    }

    public void setTauxOuverture(double tauxOuverture) {
        this.tauxOuverture = tauxOuverture;
    }

    public double getTauxConversion() {
        return tauxConversion;
    }

    public void setTauxConversion(double tauxConversion) {
        this.tauxConversion = tauxConversion;
    }
    }
