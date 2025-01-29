package net.chakir.facturationservice.entities;

import jakarta.persistence.*;
import lombok.*;
import net.chakir.facturationservice.model.Client;
import net.chakir.facturationservice.model.Commande;

import java.util.Date;
import java.util.List;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long commandeId;
    private Long clientId;
    private Double montantHT;
    private Double montantTTC;
    private Double tva;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEmission;
    @ManyToMany
    @JoinTable(
            name = "facture_taxes",
            joinColumns = @JoinColumn(name = "facture_id"),
            inverseJoinColumns = @JoinColumn(name = "taxe_id")
    )
    private List<Taxe> taxes;


    @Transient
    private Commande commande;
    @Transient
    private Client client;

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

    public Double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(Double montantHT) {
        this.montantHT = montantHT;
    }

    public Double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(Double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
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

    public Facture(Long id, Long commandeId, Long clientId, Double montantHT, Double montantTTC, Double tva, Date dateEmission, List<Taxe> taxes, Commande commande, Client client) {
        this.id = id;
        this.commandeId = commandeId;
        this.clientId = clientId;
        this.montantHT = montantHT;
        this.montantTTC = montantTTC;
        this.tva = tva;
        this.dateEmission = dateEmission;
        this.commande = commande;
        this.client = client;
    }

    public Facture() {
    }

    public List<Taxe> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Taxe> taxes) {
        this.taxes = taxes;
    }
}
