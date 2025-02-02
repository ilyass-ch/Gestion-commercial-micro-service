package net.chakir.notifservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CodePromoDetails {
    // Exemple de structure pour le code promo
    private String code;
    private String description;
    private Double montantReduction;
    private String dateExpiration;

    public CodePromoDetails() {
    }

    public CodePromoDetails(String code, String description, Double montantReduction, String dateExpiration) {
        this.code = code;
        this.description = description;
        this.montantReduction = montantReduction;
        this.dateExpiration = dateExpiration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMontantReduction() {
        return montantReduction;
    }

    public void setMontantReduction(Double montantReduction) {
        this.montantReduction = montantReduction;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
