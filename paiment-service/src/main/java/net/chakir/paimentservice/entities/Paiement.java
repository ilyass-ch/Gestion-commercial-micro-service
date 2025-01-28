package net.chakir.paimentservice.entities;

import jakarta.persistence.*;

import net.chakir.paimentservice.model.Client;
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
    private String methodPaiement;
    private String statut;
    private LocalDate date;

    @Transient
    private Commande commande;
    @Transient
    private Client client;

    public Paiement(Long id, Long commandeId, Long clientId, Double montant, String methodPaiement, String statut, LocalDate date, Commande commande, Client client) {
        this.id = id;
        this.commandeId = commandeId;
        this.clientId = clientId;
        this.montant = montant;
        this.methodPaiement = methodPaiement;
        this.statut = statut;
        this.date = date;
        this.commande = commande;
        this.client = client;
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
}
