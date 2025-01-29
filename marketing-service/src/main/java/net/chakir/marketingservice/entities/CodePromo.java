package net.chakir.marketingservice.entities;

import jakarta.persistence.*;
import net.chakir.marketingservice.enums.CodePromoStatut;

import java.util.Date;

@Entity
public class CodePromo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String description;
    private Double montantReduction;
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    private CodePromoStatut codePromoStatut;

    public CodePromo() {
    }

    public CodePromo(Long id, String code, String description, Double montantReduction, Date dateExpiration, CodePromoStatut codePromoStatut) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.montantReduction = montantReduction;
        this.dateExpiration = dateExpiration;
        this.codePromoStatut = codePromoStatut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public CodePromoStatut getCodePromoStatut() {
        return codePromoStatut;
    }

    public void setCodePromoStatut(CodePromoStatut codePromoStatut) {
        this.codePromoStatut = codePromoStatut;
    }
}
