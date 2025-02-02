package net.chakir.paimentservice.entities;

import jakarta.persistence.*;

import net.chakir.paimentservice.model.Client;
import net.chakir.paimentservice.model.CodePromo;
import net.chakir.paimentservice.model.Commande;

import java.time.LocalDate;

@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long commandeId;
    private Long clientId;
    private Double montant;
    private String clientEmail;
    private String methodPaiement;
    private String statut;
    private LocalDate date;
    private String codePromo;
    private Double reduction;

    @Transient
    private Commande commande;
    @Transient
    private Client client;
    @Transient
    private CodePromo codePromoDetails;

//    public Paiement(Long id, Long commandeId, Long clientId, Double montant,String clientEmail, String methodPaiement, String statut, LocalDate date, String codePromo, Double reduction, Commande commande, Client client, CodePromo codePromoDetails) {
//        this.id = id;
//        this.commandeId = commandeId;
//        this.clientId = clientId;
//        this.montant = montant;
//        this.methodPaiement = methodPaiement;
//        this.statut = statut;
//        this.date = date;
//        this.codePromo = codePromo;
//        this.reduction = reduction;
//        this.commande = commande;
//        this.client = client;
//        this.codePromoDetails = codePromoDetails;
//    }


    public Paiement(Long id, Long commandeId, Long clientId, Double montant,String clientEmail, String methodPaiement, String statut, LocalDate date, String codePromo, Double reduction, Commande commande, Client client, CodePromo codePromoDetails) {
        this.id = id;
        this.commandeId = commandeId;
        this.clientId = clientId;
        this.montant = montant;
        this.clientEmail = clientEmail;
        this.methodPaiement = methodPaiement;
        this.statut = statut;
        this.date = date;
        this.codePromo = codePromo;
        this.reduction = reduction;
        this.commande = commande;
        this.client = client;
        this.codePromoDetails = codePromoDetails;
    }

    public Paiement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getMethodPaiement() {
        return methodPaiement;
    }

    public void setMethodPaiement(String methodPaiement) {
        this.methodPaiement = methodPaiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CodePromo getCodePromoDetails() {
        return codePromoDetails;
    }

    public void setCodePromoDetails(CodePromo codePromoDetails) {
        this.codePromoDetails = codePromoDetails;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}